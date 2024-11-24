package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, String> {
    Professional findByCrm(String professionalCrm);
}
