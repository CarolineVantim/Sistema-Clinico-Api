package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CleanProfessionalDTO {
    private String crm;

    private String cpf;

    private String name;

    private Gender gender;

    private List<String> fieldOfWork;

    private String position;

//    private List<StudentDTO> student;

    public static CleanProfessionalDTO toDTO(Professional professional){
        CleanProfessionalDTO dto = new CleanProfessionalDTO();

        dto.setCrm(professional.getCrm());
        dto.setCpf(professional.getCpf());
        dto.setName(professional.getName());
        dto.setGender(professional.getGender());
        dto.setFieldOfWork(professional.getFieldOfWork());
        dto.setPosition(professional.getPosition());
//        dto.setStudent(StudentDTO.toListDTO(professional.getStudent()));

        return dto;
    }

    public static Professional toOBJ(CleanProfessionalDTO dto) {
        Professional professional = new Professional();

        professional.setCrm(dto.getCrm());
        professional.setCpf(dto.getCpf());
        professional.setName(dto.getName());
        professional.setGender(dto.getGender());
        professional.setFieldOfWork(dto.getFieldOfWork());
        professional.setPosition(dto.getPosition());
//        professional.setStudent(StudentDTO.toListOBJ(dto.getStudent()));

        return professional;
    }
}
