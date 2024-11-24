package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.domain.models.auth.AuthenticationRequest;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationResponse;
import com.pipa.PipaAPI.domain.models.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação e Registro de Usuários")
public interface AuthenticationSwaggerOperations {
    @Operation(summary = "Registra novos usuários no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
            @ApiResponse(responseCode = "409", description = "Username já existe", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This username is invalid\"}")))
    })
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);

    @Operation(summary = "Autenticação no sistema", description = "Faz a autenticação no sistema e retorna um JWT Token que DEVE ser aplicado no headers de qualquer outra request da API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The user with username not found\"}")))
    })
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
