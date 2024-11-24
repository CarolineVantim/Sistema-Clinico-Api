package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.ClassRecords;
import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.repository.ClassRecordsRepository;
import com.pipa.PipaAPI.rest.dto.ClassRecordsDTO;
import com.pipa.PipaAPI.rest.dto.CleanClassRecordsDTO;
import com.pipa.PipaAPI.rest.dto.CleanProfessionalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRecordsService {
    private final ClassRecordsRepository classRecordsRepository;
    private final ProfessionalService professionalService;
    private final GoogleDriveMediaService googleDriveMediaService;
    private final StudentService studentService;

    @Autowired
    public ClassRecordsService(ClassRecordsRepository classRecordsRepository,
                               ProfessionalService professionalService,
                               GoogleDriveMediaService googleDriveMediaService,
                               StudentService studentService){
        this.classRecordsRepository = classRecordsRepository;
        this.professionalService = professionalService;
        this.googleDriveMediaService = googleDriveMediaService;
        this.studentService = studentService;
    }

    public List<CleanClassRecordsDTO> findSimpleClassRecords(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ClassRecords> classRecordsPage = this.classRecordsRepository.findAll(pageable);

        return classRecordsPage.stream()
                .map(CleanClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ClassRecordsDTO findClassRecordById(Long id) {
        return ClassRecordsDTO.toDTO(this.findById(id));
    }

    public List<ClassRecordsDTO> findAllPagination(int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<ClassRecords> classRecordsPage = this.classRecordsRepository.findAll(pageable);

        return classRecordsPage.stream().map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClassRecordsDTO> findAllByClassDateBetween(LocalDate startDate, LocalDate endDate, int page){
        return this.classRecordsRepository
                .findAllByClassDateBetween(startDate, endDate, PageRequest.of(page, 5))
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClassRecordsDTO> findAllByClassDateIsAndStudentCpf(LocalDate date, String studentCpf, int page){
        return this.classRecordsRepository
                .findAllByClassDateIsAndStudentCpf(date, studentCpf, PageRequest.of(page, 5))
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClassRecordsDTO> findByProfessionalPositionAndStudentCpf(String position, String studentCpf, int page){
        return this.classRecordsRepository
                .findByProfessionalPositionAndStudentCpf(position, studentCpf, PageRequest.of(page, 5))
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClassRecordsDTO> findAllByClassDateIsAndProfessionalPositionAndStudentCpf(LocalDate date, String position, String studentCpf, int page){
        return this.classRecordsRepository
                .findAllByClassDateIsAndProfessionalPositionAndStudentCpf(date, position, studentCpf, PageRequest.of(page, 5))
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClassRecordsDTO> findClassRecordsByProfessionalCrm(int page, String professionalCrm) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        Page<ClassRecords> classRecordsPage = this.classRecordsRepository.findClassRecordsByProfessionalCrm(professionalCrm, pageable);

        return classRecordsPage.getContent()
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }    
    
    public List<ClassRecordsDTO> findClassRecordsByStudentCpf(int page, String studentCpf) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        Page<ClassRecords> classRecordsPage = this.classRecordsRepository.findClassRecordsByStudentCpf(studentCpf, pageable);

        return classRecordsPage.getContent()
                .stream()
                .map(ClassRecordsDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ClassRecordsDTO save(ClassRecordsDTO dto){
        ClassRecords classRecords = ClassRecordsDTO.toOBJ(dto);

        Professional professional = CleanProfessionalDTO.toOBJ(this.professionalService.findByIdClean(dto.getProfessional().getCrm()));
        classRecords.setProfessional(professional);
        classRecords.setMedia(null);
        Student student = this.studentService.findById(dto.getStudent().getCpf());
        classRecords.setStudent(student);

        if (dto.getMedia().getId() != 0){
            GoogleDriveMedia media = this.googleDriveMediaService.findById(dto.getMedia().getId());
            classRecords.setMedia(media);
        }

        return ClassRecordsDTO.toDTO(this.classRecordsRepository.save(classRecords));
    }

    public ClassRecordsDTO update(ClassRecordsDTO dto){
        ClassRecords classRecords = ClassRecordsDTO.toOBJ(dto);

        Professional professional = CleanProfessionalDTO.toOBJ(this.professionalService.findByIdClean(dto.getProfessional().getCrm()));
        classRecords.setProfessional(professional);
        classRecords.setMedia(null);
        Student student = this.studentService.findById(dto.getStudent().getCpf());
        classRecords.setStudent(student);

        if (dto.getMedia().getId() != 0){
            GoogleDriveMedia media = this.googleDriveMediaService.findById(dto.getMedia().getId());
            classRecords.setMedia(media);
        }

        return ClassRecordsDTO.toDTO(this.classRecordsRepository.save(classRecords));
    }

    public void delete(Long id){
        this.classRecordsRepository.delete(this.findById(id));
    }

    public ClassRecords findById(Long id){
        return this.classRecordsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The record with id '%s' not found".formatted(id)));
    }
}
