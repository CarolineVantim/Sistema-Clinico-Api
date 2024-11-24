package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.domain.entity.VideoRecords;
import com.pipa.PipaAPI.rest.dto.VideoRecordsDTO;
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

@Tag(name = "CRUD de Videos")
public interface VideoRecordsSwaggerOperations {
    @Operation(summary = "Faz a busca por todos os registros de vídeos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<VideoRecordsDTO> findAll();

    @Operation(summary = "Faz a busca do registro de um vídeo por um atributo do mesmo", description = "Busca o(s) registro(s) do(s) vídeos com as características especificadas no params, caso de um match com algum registro existente retorna o mesmo em formado de lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<VideoRecords> findByParam(VideoRecords videoRecords);

    @Operation(summary = "Faz o registro de um vídeo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registrado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    VideoRecordsDTO save(@RequestBody VideoRecordsDTO dto);

    @Operation(summary = "Realiza a atualização do registro de um vídeo", description = "Busca o registro do vídeo pelo seu ID e caso seja encontrado atualiza o mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This video with ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    VideoRecordsDTO update(@RequestBody VideoRecordsDTO dto);

    @Operation(summary = "Realiza a deleção do registro de um vídeo", description = "Busca o registro do vídeo pelo seu ID e caso seja encontrado deleta o mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This video with ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    void delete(@PathVariable Long id);
}
