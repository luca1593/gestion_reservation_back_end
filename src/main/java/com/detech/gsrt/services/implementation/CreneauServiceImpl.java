package com.detech.gsrt.services.implementation;

import com.detech.gsrt.dto.CreneauDto;
import com.detech.gsrt.modeles.Creneau;
import com.detech.gsrt.modeles.Reservation;
import com.detech.gsrt.repository.CreneauRepository;
import com.detech.gsrt.repository.ReservationRepository;
import com.detech.gsrt.services.CreneauService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service("creneauservice")
public class CreneauServiceImpl implements CreneauService {

    private CreneauRepository creneauRepository;
    private ReservationRepository reservationRepository;

    @Override
    public CreneauDto creerCreneau (CreneauDto creneau) {
        if (creneau == null){
            log.error("Le créneau {} est null ou invalide", creneau);
            throw new RuntimeException("Le créneau est null ou invalide");
        }
        return CreneauDto.fromEntity(creneauRepository.save(CreneauDto.toEntity(creneau)));
    }

    @Override
    public CreneauDto modifierCreneau (Long id, CreneauDto creneau) {

        if (creneau == null){
            log.error("Le créneau {} est null ou invalide", creneau);
            throw new RuntimeException("Le créneau est null ou invalide");
        }
        if (id == null) {
            log.error("L'id {} de la créneau n'exist pas", id);
            throw new RuntimeException("L'id du créneau n'exist pas");
        }

        Optional<Creneau> creneauOptional = creneauRepository.findById(id);
        if (creneauOptional.isPresent()) {
            creneau.setId(Math.toIntExact(id));
        }else {
            log.error("Le créneau {} n'existe pas", creneau);
            throw new RuntimeException("Le créneau n'existe pas");
        }
        return CreneauDto.fromEntity(creneauRepository.save(CreneauDto.toEntity(creneau)));
    }

    @Override
    public void supprimerCreneau (Long id) {
        if (id == null) {
            log.error("L'id {} de la créneau n'exist pas", id);
            throw new RuntimeException("L'id du créneau n'exist pas");
        }
        creneauRepository.deleteById(id);
    }

    @Override
    public List<CreneauDto> listerCreneauxDisponibles () {
        return creneauRepository.findAllByDisponibleTrue()
                .stream().map(CreneauDto::fromEntity)
                .toList();
    }

    @Override
    public CreneauDto creneauParReservation (Long reservationId) {
        if (reservationId == null) {
            log.error("L'id {} de la reservation n'exist pas", reservationId);
            throw new RuntimeException("L'id de la reservation n'exist pas");
        }
        if (!reservationRepository.findById(reservationId).isPresent()) {
            log.error("L'id {} de la reservation n'exist pas", reservationId);
            throw new EntityNotFoundException("L'id de la reservation n'exist pas");
        }
        return CreneauDto.fromEntity(creneauRepository.findByReservation_Id(reservationId));
    }
}
