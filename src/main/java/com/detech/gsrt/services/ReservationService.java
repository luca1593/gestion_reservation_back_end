package com.detech.gsrt.services;

import com.detech.gsrt.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto creerReservation(ReservationDto reservation);
    ReservationDto modifierReservation(Long id, ReservationDto reservation) throws IllegalAccessException;
    void annulerReservation(Long id) throws IllegalAccessException;
    ReservationDto trouverReservation(Long id);
    List<ReservationDto> listerReservationsParUtilisateur(Long userId);
    List<ReservationDto> listerToutesReservations();
}
