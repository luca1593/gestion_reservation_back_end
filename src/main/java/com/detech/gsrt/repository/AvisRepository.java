package com.detech.gsrt.repository;

import com.detech.gsrt.modeles.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findAllByUtilisateur_Id(Long idUtilisateur);
    List<Avis> findAllByNote(int note);
}
