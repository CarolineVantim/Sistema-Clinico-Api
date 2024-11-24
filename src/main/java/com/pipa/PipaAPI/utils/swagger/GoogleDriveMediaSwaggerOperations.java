package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.rest.dto.GoogleDriveMediaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "CRUD do Google Drive Media")
public interface GoogleDriveMediaSwaggerOperations {
    @Operation(summary = "Faz o registro de mídia do google")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mídia Criada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
    })
    @SecurityRequirement(name = "Authorization")
    GoogleDriveMediaDTO save(@RequestBody GoogleDriveMediaDTO dto);

    @Operation(summary = "Faz a atualização do registro de mídia do google", description = "Busca o registro pelo seu ID e realiza a atualização do mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mídia Atualizada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
//            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This media ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    GoogleDriveMediaDTO update(@RequestBody GoogleDriveMediaDTO dto);

    @Operation(summary = "Faz a deleção do registro de mídia do google", description = "Busca o registro pelo seu ID e realiza a deleção do mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mídia Deletada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This media ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    void delete(@PathVariable Long id);
};