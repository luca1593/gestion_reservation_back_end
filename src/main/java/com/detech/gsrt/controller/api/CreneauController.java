package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.CreneauDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", originPatterns = "*")
@RequestMapping("/creneau")
public interface CreneauController {

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CreneauDto creerCreneau(@RequestBody @NotNull @Valid CreneauDto creneau);
    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CreneauDto modifierCreneau(@PathVariable("id")  Long id, @RequestBody @NotNull @Valid CreneauDto creneau);
    @DeleteMapping(path = "/delete/{id}")
    void supprimerCreneau(@PathVariable("id") Long id);
    @GetMapping(path = "/")
    List<CreneauDto> listerCreneauxDisponibles();
}
