package com.detech.gsrt.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import com.detech.gsrt.modeles.Creneau;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @ autor : luca
 */
@Data
@Builder
public class CreneauDto {
    private Integer id;
    private LocalDate date;
    @Schema(description = "L'heure de la réservation",name = "heureDebut", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "L'heure de la réservation n'est pas valide")
    private LocalTime heureDebut;
    @Schema(description = "L'heure de fin de la réservation",name = "heureFin", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "L'heure de fin de la réservation n'est pas valide")
    private LocalTime heureFin;
    private boolean disponible;
    private ReservationDto reservationDto;
    private Instant creationDate;
    private Instant lastModifiedDate;

    public static CreneauDto fromEntity(Creneau creneau) {
        if(creneau == null) {
            return null;
        }
        return CreneauDto.builder()
                .id(creneau.getId())
				.date(creneau.getDatecrn())
                .heureDebut(creneau.getHeureDebut())
                .heureFin(creneau.getHeureFin())
                .disponible(creneau.isDisponible())
                .creationDate(creneau.getCreationDate())
                .lastModifiedDate(creneau.getLastModifiedDate())
                .reservationDto(ReservationDto.fromEtity(creneau.getReservation()))
                .build();
    }

    public static Creneau toEntity(CreneauDto creneauDto) {
        if(creneauDto == null) {
            return null;
        }

        Creneau creneau = new Creneau();
        creneau.setId(creneauDto.getId());
        creneau.setDatecrn(creneauDto.getDate());
        creneau.setHeureDebut(creneauDto.getHeureDebut());
        creneau.setHeureFin(creneauDto.getHeureFin());
        creneau.setDisponible(creneauDto.isDisponible());
        creneau.setReservation(ReservationDto.toEntity(creneauDto.getReservationDto()));
        return creneau;
    }

}
