package com.pipa.PipaAPI.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pipa.PipaAPI.domain.entity.Family;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO {
    @JsonInclude
    private String cpf;

    private String name;

    private Gender gender;

    private String address;

    private String phone;

    private String zipCode;

    private String kinshipDegree;

    private LocalDate birthDate;

    private List<User> user;

    private List<Student> students;

    public static FamilyDTO toDTO(Family family){
        FamilyDTO dto = new FamilyDTO();

        dto.setCpf(family.getCpf());
        dto.setName(family.getName());
        dto.setGender(family.getGender());
        dto.setAddress(family.getAddress());
        dto.setPhone(family.getPhone());
        dto.setZipCode(family.getZipCode());
        dto.setKinshipDegree(family.getKinshipDegree());
        dto.setBirthDate(family.getBirthDate());
        dto.setUser(family.getUser());
        dto.setStudents(family.getStudents());

        return dto;
    }

    public static Family toOBJ(FamilyDTO dto){
        Family family = new Family();

        family.setCpf(dto.getCpf());
        family.setName(dto.getName());
        family.setGender(dto.getGender());
        family.setAddress(dto.getAddress());
        family.setPhone(dto.getPhone());
        family.setZipCode(dto.getZipCode());
        family.setKinshipDegree(dto.getKinshipDegree());
        family.setBirthDate(dto.getBirthDate());
        family.setUser(dto.getUser());
        family.setStudents(dto.getStudents());

        return family;
    }
}
