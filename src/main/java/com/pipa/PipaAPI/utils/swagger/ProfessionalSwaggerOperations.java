package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.rest.dto.ProfessionalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "CRUD de Profissionais")
public interface ProfessionalSwaggerOperations {

    @Operation(summary = "Faz a busca por todos os registros de professionais existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ProfessionalDTO> findAll();

    @Operation(summary = "Faz a associação de um aluno com um profissional", description = "Busca o registro do aluno e do profissional pelos seus ID's(CPF e CRM) e realiza a associação deles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The professional or student ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    ProfessionalDTO associateStudent(@PathVariable String professionalCrm, @PathVariable String studentCpf);
}
