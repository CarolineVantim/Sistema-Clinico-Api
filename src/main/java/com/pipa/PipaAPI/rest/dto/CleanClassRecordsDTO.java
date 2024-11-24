package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.ClassRecords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CleanClassRecordsDTO {
    private Long id;

    private String professionalName;

    private String discipline;

    private LocalDate classDate;

    private String subject;

    public static CleanClassRecordsDTO toDTO(ClassRecords classRecords){
        CleanClassRecordsDTO dto = new CleanClassRecordsDTO();

        dto.setId(classRecords.getId());
        dto.setProfessionalName(classRecords.getProfessional().getName());
        dto.setClassDate(classRecords.getClassDate());
        dto.setDiscipline(classRecords.getDiscipline());
        dto.setSubject(classRecords.getSubject());

        return dto;
    }
}
