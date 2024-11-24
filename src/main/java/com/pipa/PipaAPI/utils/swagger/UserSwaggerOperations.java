package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.rest.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "CRUD de Usuários")
public interface UserSwaggerOperations {
    @Operation(summary = "Faz uma busca por todos os usuários", description = "Faz uma busca por todos os usuários retornando todos os registros em uma lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<UserDTO> findAll();

    @Operation(summary = "Faz a busca do registro de um usuário por um atributo do mesmo", description = "Busca o(s) registro(s) do(s) usuários com as características especificadas no params, caso de um match com algum registro existente retorna o mesmo em formado de lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user with param was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<User> findByParam(User user);

    @Operation(summary = "Faz a atualização do registro de um usuário", description = "Busca o registro de um usuário pelo seu ID, caso seja encontrado o mesmo é atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    UserDTO update(@RequestBody UserDTO dto);

    @Operation(summary = "Faz a deleção do registro de um usuário", description = "Busca o registro de um usuário pelo seu ID, caso seja encontrado o mesmo é deletado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    void delete(@PathVariable Long id);
}
