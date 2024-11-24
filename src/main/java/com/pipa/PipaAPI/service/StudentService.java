package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.Family;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.repository.FamilyRepository;
import com.pipa.PipaAPI.domain.repository.StudentRepository;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final  FamilyRepository familyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FamilyRepository familyRepository){
        this.studentRepository = studentRepository;
        this.familyRepository = familyRepository;
    }

    public List<StudentDTO> findAll(){
        return this.studentRepository.findAll()
                .stream()
                .map(StudentDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<Student> findByParam(Student student){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Student> example = Example.of(student, matcher);
        List<Student> studentsFound = this.studentRepository.findAll(example);

        if (studentsFound.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result for students with param is empty");
        }

        return studentsFound;
    }

    public List<StudentDTO> findByFamilyCpf(String familyCpf) {
        return this.studentRepository.findByFamilyCpf(familyCpf)
                .stream()
                .map(StudentDTO::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO save(StudentDTO dto, List<String> familyIds){
        Student student = StudentDTO.toOBJ(dto);
        List<Family> familyList = new ArrayList<>();

        for (String familyId : familyIds) {
            this.familyRepository.findById(familyId).ifPresent(familyList::add);
        }

        student.setFamily(familyList);

        return StudentDTO.toDTO(this.studentRepository.save(student));
    }

    public StudentDTO update(StudentDTO dto, List<String> familyIds){
        Student student = StudentDTO.toOBJ(dto);
        List<Family> familyList = new ArrayList<>();

        for (String familyId : familyIds) {
            this.familyRepository.findById(familyId).ifPresent(familyList::add);
        }

        student.setFamily(familyList);

        return StudentDTO.toDTO(this.studentRepository.save(student));
    }

    public void delete(String id){
        this.studentRepository.delete(this.findById(id));
    }

    public Student findById(String id){
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id '%s' not found".formatted(id)));
    }
}
