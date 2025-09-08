package com.detech.gsrt.services.implementation;

import com.detech.gsrt.dto.CreneauDto;
import com.detech.gsrt.dto.ReservationDto;
import com.detech.gsrt.dto.TableDto;
import com.detech.gsrt.dto.UtilisateurDto;
import com.detech.gsrt.modeles.Creneau;
import com.detech.gsrt.modeles.Latable;
import com.detech.gsrt.modeles.Reservation;
import com.detech.gsrt.repository.CreneauRepository;
import com.detech.gsrt.repository.ReservationRepository;
import com.detech.gsrt.services.CreneauService;
import com.detech.gsrt.services.ReservationService;
import com.detech.gsrt.services.TableService;
import com.detech.gsrt.services.UtilisateurService;
import com.detech.gsrt.utils.EntitieUtils;
import com.detech.gsrt.utils.StatutReservation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service("reservaionservice")
public class ReservationServiceImpl implements ReservationService {
    private final CreneauRepository creneauRepository;

    private ReservationRepository reservationRepository;
    private UtilisateurService utlisateurService;
    private TableService tableService;
    private CreneauService creneauService;

    @Override
    public ReservationDto creerReservation (ReservationDto reservation) {
        if (reservation == null){
            log.error("La réservation {} est null ou invalide", reservation);
            throw new RuntimeException("La réservation est null ou invalide");
        }

        UtilisateurDto utilisateurDto = null;
        if (reservation.getUtilisateur() != null) {
            utilisateurDto  = utlisateurService.trouverParId(Long.valueOf(reservation.getUtilisateur().getId()));
        }

        if (utilisateurDto != null) {
            reservation.setUtilisateur(utilisateurDto);
        }else {
            log.error("L'utilisateur {} n'exist pas pour faire une reservation", reservation.getUtilisateur());
            throw new RuntimeException("L'utilisateur n'exist pas pour faire une reservation");
        }

        TableDto tableDto = null;
        if (reservation.getTable() != null) {
            tableDto = tableService.trouverTable(Long.valueOf(reservation.getTable().getId()));
        }

        if (tableDto != null && tableDto.isDisponible()) {
            reservation.setTable(tableDto);
        }else {
            log.error("La table {} n'exist pas ou n'est plus disponible pour faire une reservation", reservation.getTable());
            throw new RuntimeException("La table n'exist pas ou n'est plus disponible pour faire une reservation");
        }

        if (!verifierDisponibilite(TableDto.toEntity(reservation.getTable()), reservation.getDate(), reservation.getCreneau())) {
            log.error("La table {} n'est pas disponible pour cette creneau horaire", reservation.getTable());
            throw new RuntimeException("La table n'est pas disponible pour cette creneau horaire");
        }

        reservation.getCreneau().setDate(reservation.getDate());
        Creneau creneau = creneauRepository.save(CreneauDto.toEntity(reservation.getCreneau()));
        reservation.setCreneau(CreneauDto.fromEntity(creneau));
        Reservation savedReservation = reservationRepository.save(ReservationDto.toEntity(reservation));
        savedReservation.setCreneau(creneau);
        creneau.setReservation(savedReservation);
        creneauRepository.save(creneau);
        return ReservationDto.fromEtity(savedReservation);
    }

    @Override
    public ReservationDto modifierReservation (Long id, ReservationDto reservation) throws IllegalAccessException {
        if (reservation == null){
            log.error("La réservation {} est null ou invalide", reservation);
            throw new RuntimeException("La réservation est null ou invalide");
        }
        if (id == null) {
            log.error("L'id {} de la réservation n'exist pas", id);
            throw new RuntimeException("L'id de la réservation n'exist pas");
        }
        ReservationDto dto = trouverReservation(id);
        CreneauDto cDto = null;
        if (dto != null) {
            reservation.setId(dto.getId());
            cDto = creneauService.creneauParReservation(Long.valueOf(dto.getId()));
        }else {
            log.error("La réservation {} n'existe pas", reservation);
            throw new RuntimeException("La réservation n'existe pas");
        }
        if (cDto != null) {
            EntitieUtils.updateIfDifferent(cDto, reservation.getCreneau());
            cDto.setDate(reservation.getDate());
            creneauRepository.save(CreneauDto.toEntity(cDto));
            dto.setCreneau(cDto);
        }
        // Trouver les different changement et applique les bons modifications
        EntitieUtils.updateIfDifferent(dto, reservation);
        dto = ReservationDto.fromEtity(reservationRepository.save(ReservationDto.toEntity(dto)));
        dto.setCreneau(cDto);
        return dto;
    }

    @Override
    public void annulerReservation (Long id) throws IllegalAccessException {
        if (id == null) {
            log.error("L'id {} de la réservation n'exist pas", id);
            throw new RuntimeException("L'id de la réservation n'exist pas");
        }
        ReservationDto dto = trouverReservation(id);
        dto.setStatut(StatutReservation.ANNULEE);
        modifierReservation(id, dto);
    }

    @Override
    public ReservationDto trouverReservation (Long id) {
        if (id == null) {
            log.error("L'id {} de la réservation n'exist pas", id);
            throw new RuntimeException("L'id de la réservation n'exist pas");
        }
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return ReservationDto.fromEtity(reservationOptional.orElse(null));
    }

    @Override
    public List<ReservationDto> listerReservationsParUtilisateur (Long userId) {
        return reservationRepository.findByUtilisateur_Id(userId)
                .stream().map(ReservationDto::fromEtity)
                .toList();
    }

    @Override
    public List<ReservationDto> listerToutesReservations () {
        return reservationRepository.findAll()
                .stream().map(ReservationDto::fromEtity)
                .toList();
    }

    public boolean verifierDisponibilite(Latable table, LocalDate date,CreneauDto nouveCreneau) {
        List<Reservation> reservationsExistantes = reservationRepository.findByTableAndDateresv(table, date);

        for (Reservation reservation : reservationsExistantes) {
            if (reservation.getCreneau() != null) {

                // Vérifie si les créneaux se chevauchent
                if (chevauche(reservation.getCreneau(), nouveCreneau)) {
                    return false; // chevauchement -> réservation impossible
                }
            }
        }
        return true;
    }

    public boolean chevauche(Creneau creneau_1, CreneauDto creneau_2) {
        // Un créneau chevauche un autre si l'heure de début est avant la fin
        // et l'heure de fin est après le début
        return (creneau_1.getHeureDebut().isBefore(creneau_2.getHeureFin())
                && creneau_1.getHeureFin().isAfter(creneau_2.getHeureDebut()));
    }
}
