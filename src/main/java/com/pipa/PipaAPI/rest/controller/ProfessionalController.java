package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.rest.dto.ProfessionalDTO;
import com.pipa.PipaAPI.service.ProfessionalService;
import com.pipa.PipaAPI.utils.swagger.ProfessionalSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/professional")
public class ProfessionalController implements ProfessionalSwaggerOperations {
    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/find_all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessionalDTO> findAll() {
        return this.professionalService.findAll();
    }

    @PostMapping("/{professionalCrm}/associate_student/{studentCpf}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProfessionalDTO associateStudent(@PathVariable String professionalCrm, @PathVariable String studentCpf){
        return this.professionalService.associateStudent(professionalCrm, studentCpf);
    }
}
