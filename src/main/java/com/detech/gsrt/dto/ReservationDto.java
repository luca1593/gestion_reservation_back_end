package com.detech.gsrt.dto;

import com.detech.gsrt.modeles.Reservation;
import com.detech.gsrt.utils.StatutReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ReservationDto {
    private Integer id;

    @NotNull(message = "La date de la réservation n'est pas valide")
    @Schema(description = "La date de la réservation",name = "date",type = "date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate date;

    @Schema(description = "Le nombre de personne pour la réservation",name = "nbPersonnes",type = "int", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "Le nombre de personne doit être supérieur ou égale à 1")
    private int nbPersonnes;
    @Schema(description = "Le statut de la réservation",name = "statut",example = "EN_ATTENTE",type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private StatutReservation statut;

    private UtilisateurDto utilisateur;
    private TableDto table;
    private CreneauDto creneau;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private AvisDto avis;


    public static ReservationDto fromEtity(Reservation reservation){
        if (reservation == null){
            return null;
        }

        return ReservationDto.builder()
                .id(reservation.getId())
                .date(reservation.getDateresv())
                .nbPersonnes(reservation.getNbPersonnes())
                .statut(reservation.getStatut())
                .creationDate(reservation.getCreationDate())
                .lastModifiedDate(reservation.getLastModifiedDate())
                .utilisateur(UtilisateurDto.fromEntity(reservation.getUtilisateur()))
                .table(TableDto.fromEntity(reservation.getTable()))
                .build();
    }

    public static Reservation toEntity(ReservationDto reservationDto){
        if(reservationDto == null){
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDateresv(reservationDto.getDate());
        reservation.setNbPersonnes(reservationDto.getNbPersonnes());
        reservation.setStatut(reservationDto.getStatut());
        reservation.setUtilisateur(UtilisateurDto.toEntity(reservationDto.getUtilisateur()));
        reservation.setTable(TableDto.toEntity(reservationDto.getTable()));
        reservation.setCreneau(CreneauDto.toEntity(reservationDto.getCreneau()));
        return reservation;
    }
}
