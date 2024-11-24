package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDTO {
    private String crm;

    private String cpf;

    private String name;

    private Gender gender;

    private List<String> fieldOfWork;

    private String address;

    private String zipCode;

    private String phone;

    private String position;

    private LocalDate birthDate;

    private List<Student> student;

    public static ProfessionalDTO toDTO(Professional professional){
        ProfessionalDTO dto = new ProfessionalDTO();

        dto.setCrm(professional.getCrm());
        dto.setCpf(professional.getCpf());
        dto.setName(professional.getName());
        dto.setGender(professional.getGender());
        dto.setFieldOfWork(professional.getFieldOfWork());
        dto.setAddress(professional.getAddress());
        dto.setZipCode(professional.getZipCode());
        dto.setPhone(professional.getPhone());
        dto.setPosition(professional.getPosition());
        dto.setBirthDate(professional.getBirthDate());
        dto.setStudent(professional.getStudent());

        return dto;
    }

    public static Professional toOBJ(ProfessionalDTO dto){
        Professional professional = new Professional();

        professional.setCrm(dto.getCrm());
        professional.setCpf(dto.getCpf());
        professional.setName(dto.getName());
        professional.setGender(dto.getGender());
        professional.setFieldOfWork(dto.getFieldOfWork());
        professional.setAddress(dto.getAddress());
        professional.setZipCode(dto.getZipCode());
        professional.setPhone(dto.getPhone());
        professional.setPosition(dto.getPosition());
        professional.setBirthDate(dto.getBirthDate());
        professional.setStudent(dto.getStudent());

        return professional;
    }
}
