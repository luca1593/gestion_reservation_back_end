package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Latable;
import com.detech.gsrt.modeles.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUtilisateur_Id(Long idUtilisateur);
    List<Reservation> findByTableAndDateresv (Latable latable, LocalDate date);
}
