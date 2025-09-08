package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.CreneauController;
import com.detech.gsrt.dto.CreneauDto;
import com.detech.gsrt.services.CreneauService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreneauControllerImpl implements CreneauController {

    private CreneauService creneauService;

    @Autowired
    public void setCreneauService (CreneauService creneauService) {
        this.creneauService = creneauService;
    }

    @Override
    public CreneauDto creerCreneau (CreneauDto creneau) {
        return creneauService.creerCreneau(creneau);
    }

    @Override
    public CreneauDto modifierCreneau (Long id, @NotNull @Valid CreneauDto creneau) {
        return creneauService.modifierCreneau(id, creneau);
    }

    @Override
    public void supprimerCreneau (Long id) {
        creneauService.supprimerCreneau(id);
    }

    @Override
    public List<CreneauDto> listerCreneauxDisponibles () {
        return creneauService.listerCreneauxDisponibles();
    }
}
