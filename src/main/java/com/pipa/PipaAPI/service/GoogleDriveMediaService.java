package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.repository.GoogleDriveMediaRepository;
import com.pipa.PipaAPI.rest.dto. GoogleDriveMediaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoogleDriveMediaService {
    private final GoogleDriveMediaRepository googleDriveMediaRepository;

    @Autowired
    public GoogleDriveMediaService(GoogleDriveMediaRepository googleDriveMediaRepository){
        this.googleDriveMediaRepository = googleDriveMediaRepository;
    }

    public List<GoogleDriveMediaDTO> findAll(){
        return this.googleDriveMediaRepository.findAll()
                .stream()
                .map(GoogleDriveMediaDTO::toDTO)
                .collect(Collectors.toList());
    };

    public GoogleDriveMediaDTO save(GoogleDriveMediaDTO dto){
        GoogleDriveMedia googleDriveMedia = GoogleDriveMediaDTO.toOBJ(dto);
        return GoogleDriveMediaDTO.toDTO(googleDriveMediaRepository.save(googleDriveMedia));
    };

    public GoogleDriveMediaDTO update(GoogleDriveMediaDTO dto){
        GoogleDriveMedia googleDriveMedia = GoogleDriveMediaDTO.toOBJ(dto);
        return GoogleDriveMediaDTO.toDTO(googleDriveMediaRepository.save(googleDriveMedia));
    };

    public void delete(Long id){
        GoogleDriveMedia googleDriveMedia = this.findById(id);
        this.googleDriveMediaRepository.delete(googleDriveMedia);
    };

    public GoogleDriveMedia findById(Long id){
        return this.googleDriveMediaRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media with id '%s' not found".formatted(id)));
    };
}