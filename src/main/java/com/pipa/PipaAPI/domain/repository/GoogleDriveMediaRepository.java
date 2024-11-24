package com.pipa.PipaAPI.domain.repository;

import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleDriveMediaRepository extends JpaRepository<GoogleDriveMedia, Long> {
}
