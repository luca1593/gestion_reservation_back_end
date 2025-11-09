package com.detech.gsrt.utils;

import lombok.Getter;

@Getter
public enum Role {
    CLIENT("Client"), GESTIONAIRE("Gestionaire");

    private final String nom;

    Role (String nom) {
        this.nom = nom;
    }

}
