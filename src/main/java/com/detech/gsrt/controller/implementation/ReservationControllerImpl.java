package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.ReservationController;
import com.detech.gsrt.dto.ReservationDto;
import com.detech.gsrt.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationControllerImpl implements ReservationController {

    private ReservationService reservationService;

    @Autowired
    public void setReservationService (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public ReservationDto creerReservation (ReservationDto reservation) {
        return reservationService.creerReservation(reservation);
    }

    @Override
    public ReservationDto modifierReservation (Long id, ReservationDto reservation) throws IllegalAccessException {
        return reservationService.modifierReservation(id, reservation);
    }

    @Override
    public void annulerReservation (Long id) throws IllegalAccessException {
        reservationService.annulerReservation(id);
    }

    @Override
    public ReservationDto trouverReservation (Long id) {
        return reservationService.trouverReservation(id);
    }

    @Override
    public List<ReservationDto> listerReservationsParUtilisateur (Long userId) {
        return reservationService.listerReservationsParUtilisateur(userId);
    }

    @Override
    public List<ReservationDto> listerToutesReservations () {
        return reservationService.listerToutesReservations();
    }
}
