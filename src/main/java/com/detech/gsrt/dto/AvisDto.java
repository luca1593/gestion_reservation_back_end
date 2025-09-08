package com.detech.gsrt.dto;

import com.detech.gsrt.modeles.Avis;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Module;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class AvisDto {
    private Integer id;
    @NotNull(message = "La note doit être entre 1 et 5")
    private int note;
    private String commentaire;
    private LocalDate date;
    @Schema(description = "Utilisateur connecte", name = "utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    private UtilisateurDto utilisateur;
    @Schema(description = "Reservation fait par l'utilisateur", name = "reservation", requiredMode = Schema.RequiredMode.REQUIRED)
    private ReservationDto reservation;
    private Instant creationDate;
    private Instant lastModifiedDate;

    public static AvisDto fromEntity(Avis avis){
        if(avis == null) {
            return null;
        }
        return AvisDto.builder()
                .id(avis.getId())
                .note(avis.getNote())
                .commentaire(avis.getCommentaire())
                .date(avis.getDateavs())
                .creationDate(avis.getCreationDate())
                .lastModifiedDate(avis.getLastModifiedDate())
                .utilisateur(UtilisateurDto.fromEntity(avis.getUtilisateur()))
                .reservation(ReservationDto.fromEtity(avis.getReservation())).build();
    }

    public static Avis toEntity(AvisDto avisDto) {
        if(avisDto == null) {
            return null;
        }

        Avis avis = new Avis();
        avis.setId(avisDto.getId());
        avis.setNote(avisDto.getNote());
        avis.setCommentaire(avisDto.getCommentaire());
        avis.setDateavs(avisDto.getDate());
        avis.setUtilisateur(UtilisateurDto.toEntity(avisDto.getUtilisateur()));
        avis.setReservation(ReservationDto.toEntity(avisDto.getReservation()));
        return avis;
    }
}
