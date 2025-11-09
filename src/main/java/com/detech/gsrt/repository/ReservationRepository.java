package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Latable;
import com.detech.gsrt.modeles.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUtilisateur_Id(Long idUtilisateur);
    List<Reservation> findByTableAndDateresv (Latable latable, LocalDate date);
}
