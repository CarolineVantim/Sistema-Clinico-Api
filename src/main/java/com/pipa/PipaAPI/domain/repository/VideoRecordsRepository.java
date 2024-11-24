package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.VideoRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRecordsRepository extends JpaRepository<VideoRecords, Long>{
}
