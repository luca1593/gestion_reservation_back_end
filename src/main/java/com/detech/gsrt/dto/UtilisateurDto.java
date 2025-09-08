package com.detech.gsrt.dto;

import com.detech.gsrt.modeles.Utilisateur;
import com.detech.gsrt.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @ autor : luca
 */
@Data
@Builder
public class UtilisateurDto {
    private Integer id;
    @NotNull(message = "Le nom de l'utilisateur est obligatoire")
    @Schema(description = "Nom de l'utilisateur",name = "nom",type = "string", example = "Adam", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 32, message = "La longueur du champ \"nom\" ne doit pas dépasser les 32 caractère.")
    private String nom;

    @Schema(description = "Adresse mail de l'utilisateur",name = "email",type = "string", example = "exemple@exemple.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Veuillez renseigner l'adress mail de l'utilisateur")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "l'adresse mail n'est pas valide")
    private String email;

    @Schema(description = "Role de l'utilisateur",name = "role",allowableValues = {"CLIENT", "GESTIONAIRE"}, example = "CLIENT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Veuillez renseigner le role de l'utilisateur")
    private Role role;
    @JsonIgnore
    private List<ReservationDto> reservationDtoList;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String password;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            return  null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .email(utilisateur.getEmail())
                .creationDate(utilisateur.getCreationDate())
                .lastModifiedDate(utilisateur.getLastModifiedDate())
                .password(utilisateur.getPassword())
                .role(utilisateur.getRole()).build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if(utilisateurDto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setRole(utilisateurDto.getRole());
        utilisateur.setPassword(utilisateurDto.getPassword());
        return utilisateur;
    }
}
