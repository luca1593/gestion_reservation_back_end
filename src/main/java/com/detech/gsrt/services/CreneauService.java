package com.detech.gsrt.services;

import com.detech.gsrt.dto.CreneauDto;

import java.util.List;

public interface CreneauService {
    CreneauDto creerCreneau(CreneauDto creneau);
    CreneauDto modifierCreneau(Long id, CreneauDto creneau);
    void supprimerCreneau(Long id);
    List<CreneauDto> listerCreneauxDisponibles();
    CreneauDto creneauParReservation(Long reservationId);

}
