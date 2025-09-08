package com.detech.gsrt.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @ autor : luca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "creneau")
public class Creneau extends AbstractEntity{
    @Column(name = "datecrn")
    private LocalDate datecrn;
    @Column(name = "heuredebut")
    private LocalTime heureDebut;
    @Column(name = "heurefin")
    private LocalTime heureFin;
    @Column(name = "disponible")
    private boolean disponible;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
