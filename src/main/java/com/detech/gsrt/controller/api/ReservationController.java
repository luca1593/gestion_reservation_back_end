package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.ReservationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", originPatterns = "*")
@RequestMapping("/reservation")
public interface ReservationController {

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ReservationDto creerReservation(@RequestBody @NotNull @Valid ReservationDto reservation);
    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ReservationDto modifierReservation(@PathVariable("id") Long id, @RequestBody @NotNull @Valid  ReservationDto reservation) throws IllegalAccessException;
    @DeleteMapping(path = "/cancel/{id}")
    void annulerReservation(@PathVariable("id") Long id) throws IllegalAccessException;
    ReservationDto trouverReservation(Long id);
    @GetMapping(path = "/byuser/{iduser}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReservationDto> listerReservationsParUtilisateur(Long userId);
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReservationDto> listerToutesReservations();

}
