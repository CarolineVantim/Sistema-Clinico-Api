package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.*;
import com.pipa.PipaAPI.domain.repository.FamilyRepository;
import com.pipa.PipaAPI.domain.repository.StudentRepository;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;


    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private ProfessionalService professionalServicee;

    @InjectMocks
    private StudentService studentService;


    @Test
    public void findAllTest(){
        List<Family> familyList1 = new ArrayList<>();
        List<Family> familyList2 = new ArrayList<>();
        Student student1 = new Student("12345678910", familyList1, "nome1", "teste", LocalDate.now(), "image_teste");
        Student student2 = new Student("12345678911", familyList2, "nome2", "teste", LocalDate.now(), "image_teste");

        when(this.studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<StudentDTO> result = this.studentService.findAll();

        assertEquals(2, result.size());
        assertEquals("12345678910", result.get(0).getCpf());
        assertEquals("12345678911", result.get(1).getCpf());
        assertEquals("nome1", result.get(0).getName());
        assertEquals("nome2", result.get(1).getName());
        assertEquals("teste", result.get(0).getDisabilityType());
        assertEquals("teste", result.get(1).getDisabilityType());
        assertEquals(LocalDate.now(), result.get(0).getBirthDate());
        assertEquals(LocalDate.now(), result.get(1).getBirthDate());
        assertEquals("image_teste", result.get(0).getStudentImage());
        assertEquals("image_teste", result.get(1).getStudentImage());
    }

    @Test
    public void saveTest(){
        StudentDTO dto = new StudentDTO();
        List<Family> familyList = new ArrayList<>();
        List<String> listIds = Arrays.asList("12345678911", "12345678912");

        dto.setCpf("12345678910");
        dto.setName("nome");
        dto.setDisabilityType("teste");
        dto.setBirthDate(LocalDate.now());
        dto.setStudentImage("image_test");

        Student savedStudent = new Student("12345678910", familyList, "nome", "teste", LocalDate.now(), "image_test");

        when(this.studentRepository.save(savedStudent)).thenReturn(savedStudent);

        StudentDTO returnedStudent = this.studentService.save(dto, listIds);
        assertNotNull(returnedStudent);
    }

    @Test
    public void updateTest(){
        List<Family> familyList = new ArrayList<>();
        List<String> listIds = Arrays.asList("12345678911", "12345678912");
        Student existingStudent = new Student("12345678910", familyList, "nome", "teste", LocalDate.now(), "image_test");

        StudentDTO updateDTO = new StudentDTO();
        updateDTO.setCpf("12345678910");
        updateDTO.setName("nome");
        updateDTO.setDisabilityType("teste");
        updateDTO.setBirthDate(LocalDate.now());
        updateDTO.setStudentImage("image_test");

        when(this.studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StudentDTO updatedStudentDTO = this.studentService.update(updateDTO, listIds);

        existingStudent.setCpf(updateDTO.getCpf());
        existingStudent.setName(updateDTO.getName());
        existingStudent.setDisabilityType(updateDTO.getDisabilityType());
        existingStudent.setBirthDate(updateDTO.getBirthDate());
        existingStudent.setStudentImage(updatedStudentDTO.getStudentImage());

        assertEquals(updateDTO.getCpf(), existingStudent.getCpf());
        assertEquals(updateDTO.getName(), existingStudent.getName());
        assertEquals(updateDTO.getDisabilityType(), existingStudent.getDisabilityType());
        assertEquals(updateDTO.getBirthDate(), existingStudent.getBirthDate());
        assertEquals(updateDTO.getStudentImage(), existingStudent.getStudentImage());

        assertEquals(updateDTO.getCpf(), updatedStudentDTO.getCpf());
        assertEquals(updateDTO.getName(), updatedStudentDTO.getName());
        assertEquals(updateDTO.getDisabilityType(), updatedStudentDTO.getDisabilityType());
        assertEquals(updateDTO.getBirthDate(), updatedStudentDTO.getBirthDate());
        assertEquals(updateDTO.getStudentImage(), updatedStudentDTO.getStudentImage());
    }

    @Test
    public void deleteTest(){
        List<Family> familyList = new ArrayList<>();
        Student student = new Student("12345678910", familyList, "nome", "teste", LocalDate.now(), "image_test");

        when(this.studentRepository.findById(student.getCpf())).thenReturn(Optional.of(student));

        this.studentService.delete(student.getCpf());

        verify(this.studentRepository, times(1)).delete(student);
    }

    @Test
    public void findByIdTest(){
        List<Family> familyList = new ArrayList<>();
        Student student = new Student("12345678910", familyList, "nome", "teste", LocalDate.now(), "image_test");

        when(this.studentRepository.findById(student.getCpf())).thenReturn(Optional.of(student));

        Student result = this.studentService.findById(student.getCpf());

        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    public void findByIdNotFoundExceptionTest() {
        try {
            Student result = this.studentService.findById(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"Student with id 'null' not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.studentService.findById(null));
        }
    }

    @Test
    public void findByParamNotFoundExceptionTest() {
        try {
            List<Student> result = this.studentService.findByParam(new Student());
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"Result for students with param is empty\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.studentService.findByParam(new Student()));
        }
    }

    @Test
    public void findByParamTest() {
        Student student = new Student();
        student.setName("Klayvert");

        when(studentRepository.findAll(any(Example.class))).thenReturn(List.of(student));

        List<Student> result = this.studentService.findByParam(student);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(student.getName(), result.get(0).getName());
    }

    @Test
    public void findByFamilyCpfTest() {
        String familyCpf = "123123";

        when(this.studentRepository.findByFamilyCpf(familyCpf)).thenReturn(List.of(new Student()));

        List<StudentDTO> result = this.studentService.findByFamilyCpf(familyCpf);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ArrayList.class, result.getClass());
    }
}
