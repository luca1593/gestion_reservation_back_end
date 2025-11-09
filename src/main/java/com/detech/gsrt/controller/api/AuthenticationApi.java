package com.detech.gsrt.controller.api;

import com.detech.gsrt.dto.auth.AuthenticationRequest;
import com.detech.gsrt.dto.auth.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author luca
 */
@CrossOrigin(origins = "*", originPatterns = "*")
@Tag(name = "Liste des API sur l'authentiication")
public interface AuthenticationApi {

    @PostMapping("/authenticate")
    @Operation(summary = "Connextion",
            description = "Cette methode permet de se logger"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access autoriser",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthenticationResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Access autoriser")
    })
   ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @NotNull @Valid AuthenticationRequest request);

}
