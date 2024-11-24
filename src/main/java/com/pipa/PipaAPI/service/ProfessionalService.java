package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.repository.ProfessionalRepository;
import com.pipa.PipaAPI.rest.dto.CleanProfessionalDTO;
import com.pipa.PipaAPI.rest.dto.ProfessionalDTO;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;

    private final StudentService studentService;

    @Autowired
    public ProfessionalService(ProfessionalRepository professionalRepository, StudentService studentService){
        this.professionalRepository = professionalRepository;
        this.studentService = studentService;
    }

    public List<ProfessionalDTO> findAll() {
        return this.professionalRepository.findAll()
                .stream()
                .map(ProfessionalDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessionalDTO associateStudent(String professionalCrm, String studentCpf){
        ProfessionalDTO dto = this.findById(professionalCrm);
        Student student = this.studentService.findById(studentCpf);
        List<Student> students = List.of(student);

        dto.setStudent(students);
        this.professionalRepository.save(ProfessionalDTO.toOBJ(dto));

        return dto;
    }

    public ProfessionalDTO findById(String crm) {
        return ProfessionalDTO.toDTO(this.professionalRepository.findById(crm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The professional with crm '%s' not found".formatted(crm))));
    }

    public CleanProfessionalDTO findByIdClean(String crm) {
        return CleanProfessionalDTO.toDTO(this.professionalRepository.findById(crm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The professional with crm '%s' not found".formatted(crm))));
    }

    public List<StudentDTO> findByProfessionalCrm(String professionalCrm) {
        Optional<Professional> professional = Optional.ofNullable(this.professionalRepository.findByCrm(professionalCrm));
        if (professional.isPresent()){
            return this.professionalRepository.findByCrm(professionalCrm)
                    .getStudent()
                    .stream()
                    .map(StudentDTO::toDTO)
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional with crm '%s' has not found".formatted(professionalCrm));
    }
}
