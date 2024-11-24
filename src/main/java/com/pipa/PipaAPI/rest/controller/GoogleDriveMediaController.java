package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.rest.dto.GoogleDriveMediaDTO;
import com.pipa.PipaAPI.service.GoogleDriveMediaService;
import com.pipa.PipaAPI.utils.swagger.GoogleDriveMediaSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/media")
public class GoogleDriveMediaController implements GoogleDriveMediaSwaggerOperations {
    private final GoogleDriveMediaService googleDriveMediaService;

    @Autowired
    public GoogleDriveMediaController(GoogleDriveMediaService GoogleDriveMediaService){
        this.googleDriveMediaService = GoogleDriveMediaService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public GoogleDriveMediaDTO save(@RequestBody GoogleDriveMediaDTO dto){
        return this.googleDriveMediaService.save(dto);
    };

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public GoogleDriveMediaDTO update(@RequestBody GoogleDriveMediaDTO dto){
        return this.googleDriveMediaService.update(dto);
    };

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        this.googleDriveMediaService.delete(id);
    };
};