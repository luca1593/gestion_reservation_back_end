package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.UtlisateurController;
import com.detech.gsrt.dto.UtilisateurDto;
import com.detech.gsrt.services.UtilisateurService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtlisateurControllerImpl implements UtlisateurController {

    private UtilisateurService utilisateurService;

    @Autowired
    public void setUtilisateurService (UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto creerUtilisateur (UtilisateurDto utilisateurDto) {
        return utilisateurService.creerUtilisateur(utilisateurDto);
    }

    @Override
    public UtilisateurDto modifierUtilisateur (Long id, @NotNull @Valid UtilisateurDto utilisateurDto) {
        return utilisateurService.modifierUtilisateur(id, utilisateurDto);
    }

    @Override
    public void supprimerUtilisateur (Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    @Override
    public UtilisateurDto trouverParId (Long id) {
        return utilisateurService.trouverParId(id);
    }

    @Override
    public UtilisateurDto trouverParEmail (String email) {
        return utilisateurService.trouverParEmail(email);
    }

    @Override
    public List<UtilisateurDto> listerUtilisateurs () {
        return utilisateurService.listerUtilisateurs();
    }
}
