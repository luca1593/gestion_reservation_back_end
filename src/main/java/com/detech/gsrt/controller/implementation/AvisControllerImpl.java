package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.AvisController;
import com.detech.gsrt.dto.AvisDto;
import com.detech.gsrt.services.AvisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvisControllerImpl implements AvisController {

    private AvisService avisService;

    @Autowired
    public void setAvisService (AvisService avisService) {
        this.avisService = avisService;
    }

    @Override
    public AvisDto ajouterAvis (AvisDto avis) {
        return avisService.ajouterAvis(avis);
    }

    @Override
    public AvisDto modifierAvis (Long id, @NotNull @Valid AvisDto avis) {
        return avisService.modifierAvis(id, avis);
    }

    @Override
    public void supprimerAvis (Long id) {
        avisService.supprimerAvis(id);
    }

    @Override
    public AvisDto trouverAvis (Long id) {
        return avisService.trouverAvis(id);
    }

    @Override
    public List<AvisDto> listerAvis () {
        return avisService.listerAvis();
    }

    @Override
    public List<AvisDto> listerAvisParUtilisateur (Long userId) {
        return avisService.listerAvisParUtilisateur(userId);
    }

    @Override
    public List<AvisDto> listerAvisParNote (int note) {
        return avisService.listerAvisParNote(note);
    }
}
