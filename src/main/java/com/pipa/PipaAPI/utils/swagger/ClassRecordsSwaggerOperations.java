package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.rest.dto.ClassRecordsDTO;
import com.pipa.PipaAPI.rest.dto.CleanClassRecordsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "CRUD de Aulas")
public interface ClassRecordsSwaggerOperations {
    @Operation(summary = "Busca todas as aulas", description = "Realiza a busca de todas as aulas registradas e traz em formato de lista paginada retornando-as de forma resumida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<CleanClassRecordsDTO> findSimpleClassRecords(@RequestParam(defaultValue = "0") int page);

    @Operation(summary = "Busca uma aula por seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The record with id not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    ClassRecordsDTO findSimpleClassRecords(@PathVariable Long id);

    @Operation(summary = "Busca todas as aulas", description = "Realiza a busca de todas as aulas registradas e traz em formato de lista paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ClassRecordsDTO> findAllPagination(@RequestParam(defaultValue = "0") int page);

    @Operation(summary = "Busca todas as aulas baseadas no crm do profissional", description = "Realiza a busca de todas as aulas registradas que tenham como responsável o Profissional com o ID informado na request, além disso os dados são retornados em formato de lista paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
        })
        @SecurityRequirement(name = "Authorization")
        List<ClassRecordsDTO> findClassRecordsByProfessionalCrm(@RequestParam(defaultValue = "0") int page, String professionalCrm);
        
    @Operation(summary = "Busca todas as aulas baseadas no cpf do aluno", description = "Realiza a busca de todas as aulas registradas baseado no cpf do aluno informado na request, além disso os dados são retornados em formato de lista paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ClassRecordsDTO> findClassRecordsByStudentCpf(@RequestParam(defaultValue = "0") int page, String studentCpf);

    @Operation(summary = "Busca todas as aulas baseadas em um intervalo de datas", description = "Realiza a busca de todas as aulas registradas presentes dentro do intervalo de data inicial e final informados na requisição, além disso os dados são retornados em formato de lista paginada. Observação: Formato da data deve ser YYYY-MM-DD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The information of a professional, student or media was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ClassRecordsDTO> findAllByClassDateBetween(@RequestParam(defaultValue = "0") int page, LocalDate startDate, LocalDate endDate);

    @Operation(summary = "Busca todas as aulas baseadas em uma data específica", description = "Realiza a busca de todas as aulas registradas na data informada na requisição, além disso os dados são retornados em formato de lista paginada. Observação: Formato da data deve ser YYYY-MM-DD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The information of a professional, student or media was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ClassRecordsDTO> findAllByClassDateIsAndStudentCpf(@RequestParam(defaultValue = "0") int page, LocalDate date, String studentCpf);

    @Operation(summary = "Busca todas as aulas baseadas na posição(cargo) de um profissional", description = "Realiza a busca de todas as aulas registradas por um profissional de um cargo(posição) específico, além disso os dados são retornados em formato de lista paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontradas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The information of a professional, student or media was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<ClassRecordsDTO> findByProfessionalPositionAndStudentCpf(@RequestParam(defaultValue = "0") int page, String position, String studentCpf);


    @Operation(summary = "Faz o registro de aulas", description = "Faz o registro de aulas buscando o ID do professional(crm), o ID do estudante(cpf) e o ID da media(caso exista) para criar o registro da aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula Criada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The information of a professional, student or media was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    ClassRecordsDTO register(@RequestBody ClassRecordsDTO dto);

    @Operation(summary = "Faz a atualização do registro de aulas", description = "Faz a atualização do registro de aulas buscando o ID do professional(crm), o ID do estudante(cpf) e o ID da media(caso exista) para criar o registro da aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula Editada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The information of a professional, student or media was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    ClassRecordsDTO update(@RequestBody ClassRecordsDTO dto);

    @Operation(summary = "Faz a deleção do registro de aulas", description = "Faz a deleção do registro de uma aula buscando a mesma pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aula Deletada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This class records register ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    void delete(@PathVariable Long id);
}
