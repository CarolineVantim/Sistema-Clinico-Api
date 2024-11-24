package com.pipa.PipaAPI.utils.swagger;

import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "CRUD de Estudantes")
public interface StudentSwaggerOperations {

    @Operation(summary = "Faz a busca por todos os estudantes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<StudentDTO> findAll();

    @Operation(summary = "Faz a busca de um estudando pelo seu cpf", description = "Busca o registro do aluno pelo seu ID(CPF)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The student ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<Student> findByParam(Student student);

    @Operation(summary = "Faz a busca de um estudando pelo CPF de um familiar que tem relacionamento com ele no banco de dados", description = "Busca o registro do aluno pelo ID(CPF) do seu familiar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<StudentDTO> findByFamilyCpf(@PathVariable String familyCpf);

    @Operation(summary = "Faz a busca de um estudando pelo CRM de um profissional que tem relacionamento com ele no banco de dados", description = "Busca o registro do aluno pelo ID(CRM) do seu cuidador/profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Estudante(s) encontrado(s)"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The professional with id has not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    List<StudentDTO> findByProfessionalCrm(@PathVariable String professionalCrm);

    @Operation(summary = "Faz o registro de um aluno", description = "Busca o(s) registro(s) do(s) familiares do aluno por meio de uma lista de strings que possui os ID's(CPF's) dos familiares. Após encontrar todos os familiares inserido no params e receber todos os dados do aluno realiza o registro do mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno Registrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The family ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    StudentDTO save(@RequestBody StudentDTO studentDTO, @RequestParam List<String> familyIds);

    @Operation(summary = "Faz a atualização do registro de um aluno", description = "Busca o(s) registro(s) do(s) familiares do aluno por meio de uma lista de strings que possui os ID's(CPF's) dos familiares. Após encontrar todos os familiares inserido no params e receber todos os dados do aluno realiza a atualização do registro do mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The family ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    StudentDTO update(@RequestBody StudentDTO studentDTO, @RequestParam List<String> familyIds);

    @Operation(summary = "Faz a deleção do registro de um aluno", description = "Busca o registro do aluno por seu ID(CPF). Após encontrar o aluno com aquele ID(CPF) realiza a deleção do registro do mesmo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com Sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"This user has no authorization the access\"}"))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"error\":\"The student ID was not found\"}")))
    })
    @SecurityRequirement(name = "Authorization")
    void delete(@PathVariable String id);
}
