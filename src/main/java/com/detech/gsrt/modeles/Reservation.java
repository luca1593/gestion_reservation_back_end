package com.detech.gsrt.modeles;

import com.detech.gsrt.utils.StatutReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @ autor : luca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "reservation")
public class Reservation extends AbstractEntity{
    @Column(name = "dateresv")
    private LocalDate dateresv;
    @Column(name = "heure")
    private LocalTime heure;
    @Column(name = "nbpersonnes")
    private int nbPersonnes;
    @Column(name = "statut")
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Latable table;

    @OneToOne(mappedBy = "reservation")
    private Creneau creneau;
    @OneToOne(mappedBy = "reservation")
    private Avis avis;
}
