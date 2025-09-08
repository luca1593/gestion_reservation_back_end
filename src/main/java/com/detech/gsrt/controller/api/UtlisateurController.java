package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.UtilisateurDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luca
 */

@CrossOrigin(origins = "*", originPatterns = "*")
@RequestMapping("/users")
public interface UtlisateurController {

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto creerUtilisateur(@RequestBody @NotNull @Valid UtilisateurDto utilisateurDto);
    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto modifierUtilisateur(@PathVariable("id") Long id, @RequestBody @NotNull @Valid UtilisateurDto utilisateurDto);
    @DeleteMapping(path = "/delete/{id}")
    void supprimerUtilisateur(@PathVariable("id") Long id);
    @GetMapping(path = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto trouverParId(@PathVariable("id") Long id);
    @GetMapping(path = "/find/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto trouverParEmail(@PathVariable("email") String email);
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> listerUtilisateurs();

}
