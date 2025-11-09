package com.detech.gsrt.services;

import com.detech.gsrt.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto) throws Exception;
    UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateurDto) throws Exception;
    void supprimerUtilisateur(Long id) throws Exception;
    UtilisateurDto trouverParId(Long id) throws Exception;
    UtilisateurDto trouverParEmail(String email) throws Exception;
    List<UtilisateurDto> listerUtilisateurs() throws Exception;
}
