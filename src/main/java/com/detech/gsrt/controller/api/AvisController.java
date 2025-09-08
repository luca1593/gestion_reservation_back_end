package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.AvisDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", originPatterns = "*")
@RequestMapping("/avis")
public interface AvisController {

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    AvisDto ajouterAvis(@RequestBody @NotNull @Valid AvisDto avis);

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    AvisDto modifierAvis(@PathVariable("id") Long id, @RequestBody @NotNull @Valid AvisDto avis);

    @DeleteMapping(path = "/delete/{id}")
    void supprimerAvis(@PathVariable("id") Long id);

    @GetMapping(path = "/find/{id}")
    AvisDto trouverAvis(@PathVariable("id") Long id);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AvisDto> listerAvis();

    @GetMapping(path = "/find-by-user/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AvisDto> listerAvisParUtilisateur(@PathVariable("userid") Long userId);

    @GetMapping(path = "/find-by-note/{note}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AvisDto> listerAvisParNote(@PathVariable("note") int note);

}
