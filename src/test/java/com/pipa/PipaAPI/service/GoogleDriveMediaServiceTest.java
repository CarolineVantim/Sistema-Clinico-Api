package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.repository.GoogleDriveMediaRepository;
import com.pipa.PipaAPI.rest.dto.GoogleDriveMediaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GoogleDriveMediaServiceTest {
    @Mock
    private GoogleDriveMediaRepository googleDriveMediaRepository;

    @InjectMocks
    private GoogleDriveMediaService googleDriveMediaService;

    @Test
    public void findAllTest(){
        GoogleDriveMedia media1 = new GoogleDriveMedia(1L, "https://teste1", "file1", ".mp4", "test video1");
        GoogleDriveMedia media2 = new GoogleDriveMedia(2L, "https://teste2", "file2", ".mp4", "test video2");

        when(this.googleDriveMediaRepository.findAll()).thenReturn(Arrays.asList(media1, media2));

        List<GoogleDriveMediaDTO> result = this.googleDriveMediaService.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        assertEquals("https://teste1", result.get(0).getUrl());
        assertEquals("https://teste2", result.get(1).getUrl());
        assertEquals("file1", result.get(0).getFileName());
        assertEquals("file2", result.get(1).getFileName());
        assertEquals(".mp4", result.get(0).getFileType());
        assertEquals(".mp4", result.get(1).getFileType());
        assertEquals("test video1", result.get(0).getDescription());
        assertEquals("test video2", result.get(1).getDescription());
    }

    @Test
    public void saveTest(){
        GoogleDriveMediaDTO dto = new GoogleDriveMediaDTO();

        dto.setId(1L);
        dto.setUrl("https://teste");
        dto.setFileName("file");
        dto.setFileType(".mp4");
        dto.setDescription("test video");

        GoogleDriveMedia savedMedia = new GoogleDriveMedia(1L, "https://teste", "file", ".mp4", "test video");

        when(this.googleDriveMediaRepository.save(savedMedia)).thenReturn(savedMedia);

        GoogleDriveMediaDTO returnedMedia = this.googleDriveMediaService.save(dto);
        assertNotNull(returnedMedia);
    }

    @Test
    public void updateTest(){
        GoogleDriveMedia existingMedia = new GoogleDriveMedia(1L, "https://teste1", "file1", ".mp4", "test video1");

        GoogleDriveMediaDTO updateDTO = new GoogleDriveMediaDTO();
        updateDTO.setId(1L);
        updateDTO.setUrl("https://teste");
        updateDTO.setFileName("file1");
        updateDTO.setFileType(".mp4");
        updateDTO.setDescription("teste video");

        when(this.googleDriveMediaRepository.save(any(GoogleDriveMedia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        GoogleDriveMediaDTO updatedMediaDTO = this.googleDriveMediaService.update(updateDTO);

        existingMedia.setId(updateDTO.getId());
        existingMedia.setUrl(updateDTO.getUrl());
        existingMedia.setFileName(updateDTO.getFileName());
        existingMedia.setFileType(updateDTO.getFileType());
        existingMedia.setDescription(updateDTO.getDescription());

        assertEquals(updateDTO.getId(), existingMedia.getId());
        assertEquals(updateDTO.getUrl(), existingMedia.getUrl());
        assertEquals(updateDTO.getFileName(), existingMedia.getFileName());
        assertEquals(updateDTO.getFileType(), existingMedia.getFileType());
        assertEquals(updateDTO.getDescription(), existingMedia.getDescription());

        assertEquals(updateDTO.getId(), updatedMediaDTO.getId());
        assertEquals(updateDTO.getUrl(), updatedMediaDTO.getUrl());
        assertEquals(updateDTO.getFileName(), updatedMediaDTO.getFileName());
        assertEquals(updateDTO.getFileType(), updatedMediaDTO.getFileType());
        assertEquals(updateDTO.getDescription(), updatedMediaDTO.getDescription());
    }

    @Test
    public void deleteTest(){
        GoogleDriveMedia media = new GoogleDriveMedia(1L, "https://teste1", "file1", ".mp4", "test video1");

        when(this.googleDriveMediaRepository.findById(media.getId())).thenReturn(Optional.of(media));

        this.googleDriveMediaService.delete(media.getId());

        verify(this.googleDriveMediaRepository, times(1)).delete(media);
    }

    @Test
    public void findByIdTest(){
        GoogleDriveMedia media = new GoogleDriveMedia(1L, "https://teste1", "file1", ".mp4", "test video1");

        when(this.googleDriveMediaRepository.findById(media.getId())).thenReturn(Optional.of(media));

        GoogleDriveMedia result = this.googleDriveMediaService.findById(media.getId());

        assertNotNull(result);
        assertEquals(media, result);
    }

    @Test
    public void findByIdNotFoundExceptionTest() {
        try {
            GoogleDriveMedia result = this.googleDriveMediaService.findById(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"Media with id 'null' not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.googleDriveMediaService.findById(null));
        }
    }
}
