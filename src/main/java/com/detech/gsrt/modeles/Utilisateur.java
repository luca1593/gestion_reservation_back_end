package com.detech.gsrt.modeles;

import com.detech.gsrt.config.Views;
import com.detech.gsrt.utils.Role;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @JsonView(Views.Internal.class)
    private String password;

    @Column(name = "active")
    private boolean isActive;

}
