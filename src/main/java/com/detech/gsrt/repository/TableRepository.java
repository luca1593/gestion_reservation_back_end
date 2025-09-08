package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Latable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Latable, Long> {
    List<Latable> findAllByDisponibleTrue();
}
