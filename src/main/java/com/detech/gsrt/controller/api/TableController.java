package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.TableDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", originPatterns = "*")
@RequestMapping("/tables")
public interface TableController {
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TableDto ajouterTable(@RequestBody @NotNull @Valid TableDto table);
    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TableDto modifierTable(@PathVariable("id") Long id,@RequestBody @NotNull @Valid TableDto table);
    @DeleteMapping(path = "/delete/{id}")
    void supprimerTable(@PathVariable("id") Long id);
    TableDto trouverTable(Long id);
    @GetMapping(path = "/disponible", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TableDto> listerTablesDisponibles();

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TableDto> listerTables();
}
