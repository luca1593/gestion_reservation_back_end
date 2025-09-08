package com.detech.gsrt.services;

import com.detech.gsrt.dto.AvisDto;

import java.util.List;

public interface AvisService {
    AvisDto ajouterAvis(AvisDto avis);
    AvisDto modifierAvis(Long id, AvisDto avis);
    void supprimerAvis(Long id);
    AvisDto trouverAvis(Long id);
    List<AvisDto> listerAvis();
    List<AvisDto> listerAvisParUtilisateur(Long userId);
    List<AvisDto> listerAvisParNote(int note);
}
