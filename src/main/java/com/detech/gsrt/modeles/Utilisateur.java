package com.detech.gsrt.modeles;

import com.detech.gsrt.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ autor : luca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity {
    @Column(name = "nom")
    private String nom;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "utilisateur")
    private List<Avis> avis;

    @Column(name = "password")
    private String password;
}
