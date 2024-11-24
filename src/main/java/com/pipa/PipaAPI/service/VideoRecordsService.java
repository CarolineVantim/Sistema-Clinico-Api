package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.VideoRecords;
import com.pipa.PipaAPI.domain.repository.VideoRecordsRepository;
import com.pipa.PipaAPI.rest.dto.VideoRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoRecordsService {
    private final VideoRecordsRepository videoRecordsRepository;

    @Autowired
    public VideoRecordsService(VideoRecordsRepository videoRecordsRepository){
        this.videoRecordsRepository = videoRecordsRepository;
    }

    public List<VideoRecordsDTO> findAll(){
        return this.videoRecordsRepository.findAll()
            .stream()
            .map(VideoRecordsDTO::toDTO)
            .collect(Collectors.toList());
    }

    public List<VideoRecords> findVideoRecordsByParam(VideoRecords videoRecords){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<VideoRecords> example = Example.of(videoRecords, matcher);
        List<VideoRecords> videoRecordsFind = this.videoRecordsRepository.findAll(example);

        if (videoRecordsFind.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result for video records with param is empty");
        }

        return videoRecordsFind;
    }

    public VideoRecordsDTO save(VideoRecordsDTO dto){
        VideoRecords videoRecords = VideoRecordsDTO.toOBJ(dto);

        return VideoRecordsDTO.toDTO(videoRecordsRepository.save(videoRecords));
    }


    public VideoRecordsDTO update(VideoRecordsDTO dto){
        VideoRecords videoRecords = VideoRecordsDTO.toOBJ(dto);
        return VideoRecordsDTO.toDTO(videoRecordsRepository.save(videoRecords));
    }

    public void delete(Long id){
        VideoRecords videoRecords = this.findById(id);
        this.videoRecordsRepository.delete(videoRecords);
    }

    public VideoRecords findById(Long id){
        return this.videoRecordsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video records with id '%s' not found".formatted(id)));
    }

}
