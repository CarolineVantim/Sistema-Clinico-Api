package com.pipa.PipaAPI.rest.dto;


import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleDriveMediaDTO {
    private Long id;

    private String url;

    private String fileName;

    private String fileType;

    private String description;

    public static GoogleDriveMediaDTO toDTO(GoogleDriveMedia googleDriveMedia){
        GoogleDriveMediaDTO dto = new GoogleDriveMediaDTO();

        dto.setId(googleDriveMedia.getId());
        dto.setUrl(googleDriveMedia.getUrl());
        dto.setFileName(googleDriveMedia.getFileName());
        dto.setFileType(googleDriveMedia.getFileType());
        dto.setDescription(googleDriveMedia.getDescription());

        return dto;
    }

    public static GoogleDriveMedia toOBJ(GoogleDriveMediaDTO dto){
        GoogleDriveMedia googleDriveMedia = new GoogleDriveMedia();

        googleDriveMedia.setId(dto.getId());
        googleDriveMedia.setUrl(dto.getUrl());
        googleDriveMedia.setFileName(dto.getFileName());
        googleDriveMedia.setFileType(dto.getFileType());
        googleDriveMedia.setDescription(dto.getDescription());

        return googleDriveMedia;
    }
}
