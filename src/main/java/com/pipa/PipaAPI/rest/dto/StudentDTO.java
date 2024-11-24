package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.Family;
import com.pipa.PipaAPI.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String cpf;

    private String name;

    private String disabilityType;

    private LocalDate birthDate;

    private String studentImage;

    public static StudentDTO toDTO(Student student){
        StudentDTO dto = new StudentDTO();

        dto.setCpf(student.getCpf());
        dto.setName(student.getName());
        dto.setDisabilityType(student.getDisabilityType());
        dto.setBirthDate(student.getBirthDate());
        dto.setStudentImage(student.getStudentImage());

        return dto;
    }

    public static Student toOBJ(StudentDTO dto){
        Student student = new Student();

        student.setCpf(dto.getCpf());
        student.setName(dto.getName());
        student.setDisabilityType(dto.getDisabilityType());
        student.setBirthDate(dto.getBirthDate());
        student.setStudentImage(dto.getStudentImage());

        return student;
    }

    public static List<Student> toListOBJ(List<StudentDTO> students) {
        if (students.isEmpty()) {
            return Collections.emptyList();
        }

        return students.stream()
                .map(student -> Student.builder()
                        .cpf(student.getCpf())
                        .name(student.getName())
                        .disabilityType(student.getDisabilityType())
                        .birthDate(student.getBirthDate())
                        .studentImage(student.getStudentImage()).build())
                .collect(Collectors.toList());
    }

    public static List<StudentDTO> toListDTO(List<Student> students) {
        if (students.isEmpty()) {
            return Collections.emptyList();
        }

        return students.stream()
                .map(student -> StudentDTO.builder()
                        .cpf(student.getCpf())
                        .name(student.getName())
                        .disabilityType(student.getDisabilityType())
                        .birthDate(student.getBirthDate())
                        .studentImage(student.getStudentImage()).build())
                .collect(Collectors.toList());
    }
}
