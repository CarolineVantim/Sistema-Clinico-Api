package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.rest.dto.ClassRecordsDTO;
import com.pipa.PipaAPI.rest.dto.CleanClassRecordsDTO;
import com.pipa.PipaAPI.service.ClassRecordsService;
import com.pipa.PipaAPI.utils.swagger.ClassRecordsSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/class_records")
public class ClassRecordsController implements ClassRecordsSwaggerOperations {
    private final ClassRecordsService classRecordsService;

    @Autowired
    public ClassRecordsController(ClassRecordsService classRecordsService){
        this.classRecordsService = classRecordsService;
    }

    @GetMapping("/find_resume_class")
    @ResponseStatus(HttpStatus.OK)
    public List<CleanClassRecordsDTO> findSimpleClassRecords(@RequestParam(defaultValue = "0") int page){
        return this.classRecordsService.findSimpleClassRecords(page);
    }

    @GetMapping("/find_class_by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassRecordsDTO findSimpleClassRecords(@PathVariable Long id){
        return this.classRecordsService.findClassRecordById(id);
    }

    @GetMapping("/find_class")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findAllPagination(@RequestParam(defaultValue = "0") int page){
        return this.classRecordsService.findAllPagination(page);
    }

    @GetMapping("/find_class_by_professional_crm")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findClassRecordsByProfessionalCrm(@RequestParam(defaultValue = "0") int page, String professionalCrm){
        return this.classRecordsService.findClassRecordsByProfessionalCrm(page, professionalCrm);
    }

    @GetMapping("/find_class_by_student_cpf")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findClassRecordsByStudentCpf(@RequestParam(defaultValue = "0") int page, String studentCpf){
        return this.classRecordsService.findClassRecordsByStudentCpf(page, studentCpf);
    }

    @GetMapping("/find_class_by_date_interval")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findAllByClassDateBetween(@RequestParam(defaultValue = "0") int page, LocalDate startDate, LocalDate endDate){
        return this.classRecordsService.findAllByClassDateBetween(startDate, endDate, page);
    }

    @GetMapping("/find_class_by_date")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findAllByClassDateIsAndStudentCpf(@RequestParam(defaultValue = "0") int page, LocalDate date, String studentCpf){
        return this.classRecordsService.findAllByClassDateIsAndStudentCpf(date, studentCpf, page);
    }

    @GetMapping("/find_class_by_professional_position")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findByProfessionalPositionAndStudentCpf(@RequestParam(defaultValue = "0") int page, String position, String studentCpf){
        return this.classRecordsService.findByProfessionalPositionAndStudentCpf(position, studentCpf, page);
    }

    @GetMapping("/find_class_by_professional_position_and_class_date")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRecordsDTO> findAllByClassDateIsAndProfessionalPosition(@RequestParam(defaultValue = "0") int page, LocalDate date, String position, String studentCpf){
        return this.classRecordsService.findAllByClassDateIsAndProfessionalPositionAndStudentCpf(date, position, studentCpf, page);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassRecordsDTO register(@RequestBody ClassRecordsDTO dto){
        return this.classRecordsService.save(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ClassRecordsDTO update(@RequestBody ClassRecordsDTO dto){
        return this.classRecordsService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        this.classRecordsService.delete(id);
    }
}
