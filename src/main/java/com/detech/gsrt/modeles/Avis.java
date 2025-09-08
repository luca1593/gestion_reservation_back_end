package com.detech.gsrt.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "avis")
public class Avis extends AbstractEntity{
    @Column(name = "note")
    private int note;
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "dateavs")
    private LocalDate dateavs;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
