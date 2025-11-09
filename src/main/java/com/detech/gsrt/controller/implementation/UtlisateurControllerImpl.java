package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.UtlisateurController;
import com.detech.gsrt.dto.UtilisateurDto;
import com.detech.gsrt.exception.ErrorWS;
import com.detech.gsrt.services.UtilisateurService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> creerUtilisateur (UtilisateurDto utilisateurDto) {

        try{
            UtilisateurDto dto = utilisateurService.creerUtilisateur(utilisateurDto);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            ErrorWS errorWS = new ErrorWS();
            errorWS.setCode(500);
            errorWS.setMessage("Email deja utiliser sur un autre compte.");
            return ResponseEntity.internalServerError().body(errorWS);
        }

    }

    @Override
    public ResponseEntity<?> modifierUtilisateur (Long id, @NotNull @Valid UtilisateurDto utilisateurDto) {
        try{
            UtilisateurDto dto = utilisateurService.modifierUtilisateur(id, utilisateurDto);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            ErrorWS errorWS = new ErrorWS();
            errorWS.setCode(500);
            errorWS.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(errorWS);
        }
    }

    @Override
    public void supprimerUtilisateur (Long id) {
        try {
            utilisateurService.supprimerUtilisateur(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> trouverParId (Long id) {
        try{
            UtilisateurDto dto = utilisateurService.trouverParId(id);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            ErrorWS errorWS = new ErrorWS();
            errorWS.setCode(500);
            errorWS.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(errorWS);
        }

    }

    @Override
    public ResponseEntity<?> trouverParEmail (String email) {

        try{
            UtilisateurDto dto = utilisateurService.trouverParEmail(email);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            ErrorWS errorWS = new ErrorWS();
            errorWS.setCode(500);
            errorWS.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(errorWS);
        }
    }

    @Override
    public ResponseEntity<?> listerUtilisateurs () {

        try {
            List<UtilisateurDto> dtoList = utilisateurService.listerUtilisateurs();
            return ResponseEntity.ok().body(dtoList);
        } catch (Exception e) {
            ErrorWS errorWS = new ErrorWS();
            errorWS.setCode(500);
            errorWS.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(errorWS);
        }

    }
}
