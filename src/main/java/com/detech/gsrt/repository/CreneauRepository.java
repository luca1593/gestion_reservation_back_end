package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {
    List<Creneau> findAllByDisponibleTrue();
    Creneau findByReservation_Id(Long reservationId);
}
