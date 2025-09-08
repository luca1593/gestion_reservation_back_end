package com.detech.gsrt.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "latable")
public class Latable extends AbstractEntity{
    @Column(name = "nom", unique = true)
    private String nom;
    @Column(name = "capacite")
    private int capacite;
    @Column(name = "disponile")
    private boolean disponible;
    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;
}
