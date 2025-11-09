package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findAllByUtilisateur_Id(Long idUtilisateur);
    List<Avis> findAllByNote(int note);
}
