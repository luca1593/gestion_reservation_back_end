package com.detech.gsrt.controller.api;

import com.detech.gsrt.config.Views;
import com.detech.gsrt.dto.UtilisateurDto;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author luca
 */

@CrossOrigin(origins = "*", originPatterns = "*")
@Tag(name = "Liste des API sur les utilisateurs")
@RequestMapping("/users")
public interface UtlisateurController {

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> creerUtilisateur(@RequestBody @NotNull @Valid UtilisateurDto utilisateurDto);

    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> modifierUtilisateur(@PathVariable("id") Long id, @RequestBody @NotNull @Valid UtilisateurDto utilisateurDto);

    @DeleteMapping(path = "/delete/{id}")
    void supprimerUtilisateur(@PathVariable("id") Long id);

    @GetMapping(path = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> trouverParId(@PathVariable("id") Long id);

    @GetMapping(path = "/find/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> trouverParEmail(@PathVariable("email") String email);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> listerUtilisateurs();

}
