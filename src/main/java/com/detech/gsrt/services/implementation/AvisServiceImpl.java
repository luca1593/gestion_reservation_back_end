package com.detech.gsrt.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.detech.gsrt.dto.AvisDto;
import com.detech.gsrt.repository.AvisRepository;
import com.detech.gsrt.services.AvisService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service("avisservice")
public class AvisServiceImpl implements AvisService {

    private AvisRepository avisRepository;

    @Override
    public AvisDto ajouterAvis (AvisDto avis) {
        if (avis == null){
            log.error("L'avis {} est null ou invalide", avis);
            throw new RuntimeException("L'avis est null ou invalide");
        }
        return AvisDto.fromEntity(avisRepository.save(AvisDto.toEntity(avis)));
    }

    @Override
    public AvisDto modifierAvis (Long id, AvisDto avis) {
        if (id == null) {
            log.error("L'id {} de la créneau n'exist pas", id);
            throw new RuntimeException("L'id du créneau n'exist pas");
        }
        if (avis == null){
            log.error("L'avis {} est null ou invalide", avis);
            throw new RuntimeException("L'avis est null ou invalide");
        }
        AvisDto dto = trouverAvis(id);
        if (dto != null) {
            avis.setId(Math.toIntExact(id));
        }else {
            log.error("Aucun avis n'exist avec l'id {}", id);
            throw new RuntimeException("Aucun avis n'exist avec l'id " + id);
        }

        return AvisDto.fromEntity(avisRepository.save(AvisDto.toEntity(avis)));
    }

    @Override
    public void supprimerAvis (Long id) {
        if (id == null) {
            log.error("L'id {} de l'avis n'exist pas", id);
            throw new RuntimeException("L'id de l'avis n'exist pas");
        }

        AvisDto dto = trouverAvis(id);
        if (dto != null) {
            avisRepository.deleteById(id);
        }else {
            log.error("Aucun avis n'exist avec l'id {}", id);
            throw new RuntimeException("Aucun avis n'exist avec l'id " + id);
        }
    }

    @Override
    public AvisDto trouverAvis (Long id) {
        if (id == null) {
            log.error("L'id {} de l'avis n'exist pas", id);
            throw new RuntimeException("L'id de l'avis n'exist pas");
        }
        return AvisDto.fromEntity(avisRepository.findById(id).orElse(null));
    }

    @Override
    public List<AvisDto> listerAvis () {
        return avisRepository.findAll().stream()
                .map(AvisDto::fromEntity)
                .toList();
    }

    @Override
    public List<AvisDto> listerAvisParUtilisateur (Long userId) {
        return avisRepository.findAllByUtilisateur_Id(userId)
                .stream().map(AvisDto::fromEntity)
                .toList();
    }

    @Override
    public List<AvisDto> listerAvisParNote (int note) {
        return avisRepository.findAllByNote(note)
                .stream().map(AvisDto::fromEntity)
                .toList();
    }
}
