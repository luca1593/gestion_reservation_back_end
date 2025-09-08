package com.detech.gsrt.services.implementation;

import com.detech.gsrt.dto.UtilisateurDto;
import com.detech.gsrt.repository.UtilisateurRepository;
import com.detech.gsrt.services.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("utilisateurservice")
@Slf4j
@AllArgsConstructor
public class UtlisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Override
    public UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            log.error("L'utilisateur {} est invalide ou null.", utilisateurDto);
            throw new RuntimeException("Utilisateur invalide ou null.");
        }
        utilisateurDto.setPassword(/*generateEncodedPassword(*/utilisateurDto.getPassword());
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateur) {

        if (id == null) {
            log.error("L'id de l'utilisateur {} est invalide ou null.", id);
            throw new RuntimeException("L'id de l'utilisateur invalide ou null.");
        }
        if (utilisateur == null) {
            log.error("L'utilisateur {} est invalide ou null.", id);
            throw new RuntimeException("Utilisateur invalide ou null.");
        }

        UtilisateurDto dto = trouverParId(id);
        if(dto != null) {
            utilisateur.setId(dto.getId());
        }else {
            log.error("Aucun utilisateur n'exist avec l'id {}", id);
            throw new RuntimeException("Aucun utilisateur n'exist avec l'id " + id);
        }

        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateur)));
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        if (id == null){
            log.error("L'utilisateur {} est invalide ou null.", id);
            throw new RuntimeException("L'id de l'utilisateur est null");
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto trouverParId(Long id) {
        if (id == null){
            log.error("L'utilisateur {} est invalide ou null.", id);
            throw new RuntimeException("L'id de l'utilisateur est null");
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.findById(id).orElse(null));
    }

    @Override
    public UtilisateurDto trouverParEmail(String email) {
        if (!StringUtils.hasLength(email)){
            log.error("L'utilisateur {} est invalide ou null.", email);
            throw new RuntimeException("L'adresse mail de l'utilisateur est null");
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.findByEmail(email).orElse(null));
    }

    @Override
    public List<UtilisateurDto> listerUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDto::fromEntity)
                .toList();
    }
/*
    private String generateEncodedPassword(String motDePasse) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(motDePasse);
    }
*/
}
