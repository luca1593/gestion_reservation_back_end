package com.detech.gsrt.dto;

import com.detech.gsrt.modeles.Latable;
import com.detech.gsrt.modeles.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class TableDto {
    private Integer id;
    @NotNull(message = "Le nom de la table est obligatoire")
    private String nom;
    @NotNull(message = "La capacité de la table doit être surperieur ou égale à 1")
    private int capacite;
    private boolean disponible;
    @JsonIgnore
    private List<ReservationDto> reservationDtoList;
    private Instant creationDate;
    private Instant lastModifiedDate;

    public static TableDto fromEntity(Latable table){
        if(table == null){
            return null;
        }

        return TableDto.builder()
                .id(table.getId())
                .nom(table.getNom())
                .capacite(table.getCapacite())
                .creationDate(table.getCreationDate())
                .lastModifiedDate(table.getLastModifiedDate())
                .disponible(table.isDisponible()).build();
    }

    public static Latable toEntity(TableDto tableDto){
        if(tableDto == null){
            return null;
        }

        Latable table = new Latable();
        table.setId(tableDto.getId());
        table.setNom(tableDto.getNom());
        table.setCapacite(tableDto.getCapacite());
        table.setDisponible(tableDto.isDisponible());
        return table;
    }
}
