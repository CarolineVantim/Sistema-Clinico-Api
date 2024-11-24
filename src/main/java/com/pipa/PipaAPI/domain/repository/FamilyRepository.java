package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, String> {
}
