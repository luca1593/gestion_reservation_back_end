package com.detech.gsrt.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Schema(description = "Adresse mail de l'utilisateur",name = "login",type = "string", example = "exemple@exemple.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Veuillez renseigner l'adresse mail de l'utilisateur")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "l'adresse mail n'est pas valide")
    private String login;
    @Schema(description = "Mot de passe de l'utilisateur",name = "password",type = "string", example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Veuillez renseigner le mot de passe de l'utilisateur")
    private String password;
}
