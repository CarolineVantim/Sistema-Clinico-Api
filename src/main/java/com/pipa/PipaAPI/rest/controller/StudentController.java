package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import com.pipa.PipaAPI.service.ProfessionalService;
import com.pipa.PipaAPI.service.StudentService;
import com.pipa.PipaAPI.utils.swagger.StudentSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student")
public class StudentController implements StudentSwaggerOperations {
    private final StudentService studentService;
    private final ProfessionalService professionalService;

    @Autowired
    public StudentController(StudentService studentService, ProfessionalService professionalService){
        this.studentService = studentService;
        this.professionalService = professionalService;
    }

    @GetMapping("/view_all")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findAll(){
        return this.studentService.findAll();
    }

    @GetMapping("/find_by")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> findByParam(Student student){
        return this.studentService.findByParam(student);
    }

    @GetMapping("/find_students_by_family/{familyCpf}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findByFamilyCpf(@PathVariable String familyCpf){
        return this.studentService.findByFamilyCpf(familyCpf);
    }

    @GetMapping("/find_students_by_professional/{professionalCrm}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findByProfessionalCrm(@PathVariable String professionalCrm){
        return this.professionalService.findByProfessionalCrm(professionalCrm);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO save(@RequestBody StudentDTO studentDTO, @RequestParam List<String> familyIds){
        return this.studentService.save(studentDTO, familyIds);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO update(@RequestBody StudentDTO studentDTO, @RequestParam List<String> familyIds){
        return this.studentService.update(studentDTO, familyIds);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        this.studentService.delete(id);
    }
}
