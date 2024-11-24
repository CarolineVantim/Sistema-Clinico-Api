package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.ClassRecords;
import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.enums.StatusClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRecordsDTO {
    private Long id;

    private CleanProfessionalDTO professional;

    private LocalDate classDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String subject;

    private StatusClass status;

    private String location;

    private String discipline;

    private List<String> notes;

    private GoogleDriveMedia media;

    private StudentDTO student;

    public static ClassRecordsDTO toDTO(ClassRecords classRecords){
        ClassRecordsDTO dto = new ClassRecordsDTO();

        dto.setId(classRecords.getId());
        dto.setProfessional(CleanProfessionalDTO.toDTO(classRecords.getProfessional()));
        dto.setClassDate(classRecords.getClassDate());
        dto.setStartTime(classRecords.getStartTime());
        dto.setEndTime(classRecords.getEndTime());
        dto.setSubject(classRecords.getSubject());
        dto.setStatus(classRecords.getStatus());
        dto.setLocation(classRecords.getLocation());
        dto.setDiscipline(classRecords.getDiscipline());
        dto.setNotes(classRecords.getNotes());
        dto.setMedia(classRecords.getMedia());
        dto.setStudent(StudentDTO.toDTO(classRecords.getStudent()));

        return dto;
    }

    public static ClassRecords toOBJ(ClassRecordsDTO dto){
        ClassRecords classRecords = new ClassRecords();

        classRecords.setId(dto.getId());
        classRecords.setProfessional(CleanProfessionalDTO.toOBJ(dto.getProfessional()));
        classRecords.setClassDate(dto.getClassDate());
        classRecords.setStartTime(dto.getStartTime());
        classRecords.setEndTime(dto.getEndTime());
        classRecords.setSubject(dto.getSubject());
        classRecords.setStatus(dto.getStatus());
        classRecords.setLocation(dto.getLocation());
        classRecords.setDiscipline(dto.getDiscipline());
        classRecords.setNotes(dto.getNotes());
        classRecords.setMedia(dto.getMedia());
        classRecords.setStudent(StudentDTO.toOBJ(dto.getStudent()));

        return classRecords;
    }
}
