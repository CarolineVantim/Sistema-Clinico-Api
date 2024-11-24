package com.pipa.PipaAPI.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pipa.PipaAPI.domain.enums.Gender;
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
@Table(name = "professional")
public class Professional {
    @Id
    @Column(name = "crm", length = 20, unique = true, nullable = false)
    private String crm;

    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "field_of_works")
    @Column(name = "field_of_work")
    private List<String> fieldOfWork;

    @Column(name = "address")
    private String address;

    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "professional")
    private List<User> user;

    @ManyToMany
    @JoinTable(name = "professional_student",
            joinColumns = @JoinColumn(name = "professional_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> student;
}
