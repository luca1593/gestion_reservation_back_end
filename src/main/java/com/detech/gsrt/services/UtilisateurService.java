package com.detech.gsrt.services;

import com.detech.gsrt.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateurDto);
    void supprimerUtilisateur(Long id);
    UtilisateurDto trouverParId(Long id);
    UtilisateurDto trouverParEmail(String email);
    List<UtilisateurDto> listerUtilisateurs();
}
