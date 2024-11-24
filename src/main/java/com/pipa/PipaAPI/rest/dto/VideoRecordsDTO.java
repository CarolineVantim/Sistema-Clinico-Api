package com.pipa.PipaAPI.rest.dto;

import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.entity.VideoRecords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRecordsDTO {
    private Long id;

    private GoogleDriveMedia media;

    private LocalDate recordingDate;

    private String description;

    private String videoUrl;

    public static VideoRecordsDTO toDTO(VideoRecords videoRecords){
        VideoRecordsDTO dto = new VideoRecordsDTO();

        dto.setId(videoRecords.getId());
        dto.setMedia(videoRecords.getMedia());
        dto.setRecordingDate(videoRecords.getRecordingDate());
        dto.setDescription(videoRecords.getDescription());
        dto.setVideoUrl(videoRecords.getVideoUrl());

        return dto;
    }

    public static VideoRecords toOBJ(VideoRecordsDTO dto){
        VideoRecords videoRecords = new VideoRecords();

        videoRecords.setId(dto.getId());
        videoRecords.setMedia(dto.getMedia());
        videoRecords.setRecordingDate(dto.getRecordingDate());
        videoRecords.setDescription(dto.getDescription());
        videoRecords.setVideoUrl(dto.getVideoUrl());

        return videoRecords;
    }
}
