package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.ClassRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClassRecordsRepository extends JpaRepository<ClassRecords, Long> {
    Page<ClassRecords> findClassRecordsByProfessionalCrm(@Param("professionalCrm") String professionalCrm, Pageable pageable);

    Page<ClassRecords> findClassRecordsByStudentCpf(@Param("studentCpf") String studentCpf, Pageable pageable);

    Page<ClassRecords> findByProfessionalPositionAndStudentCpf(@Param("position") String position, String studentCpf, Pageable pageable);

    Page<ClassRecords> findAllByClassDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<ClassRecords> findAllByClassDateIsAndStudentCpf(LocalDate date, String studentCpf, Pageable pageable);

    Page<ClassRecords> findAllByClassDateIsAndProfessionalPositionAndStudentCpf(LocalDate date, String position, String studentCpf, Pageable pageable);
}
