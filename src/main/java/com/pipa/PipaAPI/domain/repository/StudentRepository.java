package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByFamilyCpf(String familyCpf);
}
