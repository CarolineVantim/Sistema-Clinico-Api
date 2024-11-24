package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.Gender;
import com.pipa.PipaAPI.domain.repository.ProfessionalRepository;
import com.pipa.PipaAPI.rest.dto.ProfessionalDTO;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessionalServiceTest {
    @Mock
    StudentService studentService;

    @Mock
    ProfessionalRepository professionalRepository;

    @InjectMocks
    ProfessionalService professionalService;

    @Test
    public void findAllTest() {
        List<User> users = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        Professional professional1 = new Professional("123456", "40028922", "teste1", Gender.M, List.of("fisioterapeuta", "psicologo"), "rua teste", "122240-60", "(19) 99999-9999", "fisioterapeuta", LocalDate.now(), users, students);
        Professional professional2 = new Professional("654321", "22982004", "teste2", Gender.F, List.of("fisioterapeuta", "psicologo"), "rua teste", "122240-60", "(19) 99999-9999", "fisioterapeuta", LocalDate.now(), users, students);

        when(this.professionalRepository.findAll()).thenReturn(Arrays.asList(professional1, professional2));

        List<ProfessionalDTO> result = this.professionalService.findAll();

        assertEquals(2, result.size());
        assertEquals("123456", result.get(0).getCrm());
        assertEquals("654321", result.get(1).getCrm());
        assertEquals("40028922", result.get(0).getCpf());
        assertEquals("22982004", result.get(1).getCpf());
        assertEquals("teste1", result.get(0).getName());
        assertEquals("teste2", result.get(1).getName());
        assertEquals(Gender.M, result.get(0).getGender());
        assertEquals(Gender.F, result.get(1).getGender());
        assertEquals(List.of("fisioterapeuta", "psicologo"), result.get(0).getFieldOfWork());
        assertEquals(List.of("fisioterapeuta", "psicologo"), result.get(1).getFieldOfWork());
        assertEquals("rua teste", result.get(0).getAddress());
        assertEquals("rua teste", result.get(1).getAddress());
        assertEquals("122240-60", result.get(0).getZipCode());
        assertEquals("122240-60", result.get(1).getZipCode());
        assertEquals("(19) 99999-9999", result.get(0).getPhone());
        assertEquals("(19) 99999-9999", result.get(1).getPhone());
        assertEquals("fisioterapeuta", result.get(0).getPosition());
        assertEquals("fisioterapeuta", result.get(1).getPosition());
        assertEquals(LocalDate.now(), result.get(0).getBirthDate());
        assertEquals(LocalDate.now(), result.get(0).getBirthDate());
        assertEquals(ArrayList.class, users.getClass());
        assertEquals(ArrayList.class, students.getClass());
        assertEquals(0, users.size());
        assertEquals(0, students.size());
    }

    @Test
    public void findByIdTest() {
        String crm = "123456";

        when(this.professionalRepository.findById(crm)).thenReturn(Optional.of(new Professional()));

        ProfessionalDTO result = this.professionalService.findById(crm);

        assertNotNull(result);
        assertEquals(new ProfessionalDTO(), result);
    }

    @Test
    public void findByIdNotFoundExceptionTest() {
        try {
            ProfessionalDTO result = this.professionalService.findById(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"The professional with crm 'null' not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.professionalService.findById(null));
        }
    }

    @Test
    public void associateStudentTest() {
        String professionalCrm = "123456";
        String studentCpf = "12345678910";

        Professional professional = new Professional();
        professional.setCrm(professionalCrm);

        Student student = new Student();
        student.setCpf(studentCpf);

        when(this.professionalRepository.findById(professionalCrm)).thenReturn(Optional.of(new Professional()));
        when(this.studentService.findById(studentCpf)).thenReturn(student);

        ProfessionalDTO result = this.professionalService.associateStudent(professionalCrm, studentCpf);

        assertNotNull(result);
        assertEquals(ProfessionalDTO.class, result.getClass());
    }

    @Test
    public void findByProfessionalCrmTest() {
        Professional professional = new Professional();
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        professional.setStudent(students);

        when(professionalRepository.findByCrm("123456")).thenReturn(professional);

        List<StudentDTO> result = professionalService.findByProfessionalCrm("123456");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ArrayList.class, result.getClass());
    }

    @Test
    public void findByProfessionalCrmNotFoundTest() {
        try {
            List<StudentDTO> result = this.professionalService.findByProfessionalCrm(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"Professional with crm 'null' has not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.professionalService.findByProfessionalCrm(null));
        }
    }
}
