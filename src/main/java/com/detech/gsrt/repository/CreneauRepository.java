package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CreneauRepository extends JpaRepository<Creneau, Long> {
    List<Creneau> findAllByDisponibleTrue();
    Creneau findByReservation_Id(Long reservationId);
}
