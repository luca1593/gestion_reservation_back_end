package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Latable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TableRepository extends JpaRepository<Latable, Long> {
    List<Latable> findAllByDisponibleTrue();
}
