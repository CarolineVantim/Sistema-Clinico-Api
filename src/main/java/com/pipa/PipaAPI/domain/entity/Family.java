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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family")
public class Family {
    @Id
    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @Column(name = "kinship_degree", length = 50)
    private String kinshipDegree;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "family")
    private List<User> user;

    @JsonIgnore
    @ManyToMany(mappedBy = "family")
    private List<Student> students;
}
