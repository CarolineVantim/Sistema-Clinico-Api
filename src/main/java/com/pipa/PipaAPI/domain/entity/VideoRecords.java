package com.pipa.PipaAPI.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video_records")
public class VideoRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_google_drive_media", referencedColumnName = "id")
    private GoogleDriveMedia media;

    @Column(name = "recording_date")
    private LocalDate recordingDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @PrePersist
    protected void onCreate(){
        recordingDate = LocalDate.now();
    }
}
