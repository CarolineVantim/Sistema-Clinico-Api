package com.pipa.PipaAPI.domain.entity;

import com.pipa.PipaAPI.domain.enums.StatusClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_records")
public class ClassRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professional_crm", referencedColumnName = "crm")
    private Professional professional;

    @Column(name = "class_date")
    private LocalDate classDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "subject")
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private StatusClass status;

    @Column(name = "location")
    private String location;

    @Column(name = "discipline")
    private String discipline;

    @ElementCollection
    @CollectionTable(name = "notes")
    @Column(name = "notes", columnDefinition = "TEXT")
    private List<String> notes;

    @ManyToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private GoogleDriveMedia media;

    @ManyToOne
    @JoinColumn(name = "student_cpf", referencedColumnName = "cpf")
    private Student student;
}
