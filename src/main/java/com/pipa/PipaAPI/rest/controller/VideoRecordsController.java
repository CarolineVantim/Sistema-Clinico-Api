package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.domain.entity.VideoRecords;
import com.pipa.PipaAPI.rest.dto.VideoRecordsDTO;
import com.pipa.PipaAPI.service.VideoRecordsService;
import com.pipa.PipaAPI.utils.swagger.VideoRecordsSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/video")
public class VideoRecordsController implements VideoRecordsSwaggerOperations {
    private final VideoRecordsService videoRecordsService;

    @Autowired
    public VideoRecordsController(VideoRecordsService videoRecordsService){
        this.videoRecordsService = videoRecordsService;
    }

    @GetMapping("/view_videos")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoRecordsDTO> findAll(){
        return this.videoRecordsService.findAll();
    }

    @GetMapping("/find_by")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoRecords> findByParam(VideoRecords videoRecords){
        return this.videoRecordsService.findVideoRecordsByParam(videoRecords);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public VideoRecordsDTO save(@RequestBody VideoRecordsDTO dto){
        return this.videoRecordsService.save(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public VideoRecordsDTO update(@RequestBody VideoRecordsDTO dto){
        return this.videoRecordsService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        this.videoRecordsService.delete(id);
    }
}
