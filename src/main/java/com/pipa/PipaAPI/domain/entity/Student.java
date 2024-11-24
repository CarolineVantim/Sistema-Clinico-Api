package com.pipa.PipaAPI.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @ManyToMany
    @JoinTable(name = "student_family",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "family_id")
    )
    private List<Family> family;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "disability_type", length = 50)
    private String disabilityType;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "student_image", columnDefinition = "TEXT")
    private String studentImage;
}
