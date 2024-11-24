package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.ClassRecords;
import com.pipa.PipaAPI.domain.entity.GoogleDriveMedia;
import com.pipa.PipaAPI.domain.entity.Student;
import com.pipa.PipaAPI.domain.enums.StatusClass;
import com.pipa.PipaAPI.domain.repository.ClassRecordsRepository;
import com.pipa.PipaAPI.domain.repository.FamilyRepository;
import com.pipa.PipaAPI.rest.dto.ClassRecordsDTO;
import com.pipa.PipaAPI.rest.dto.CleanProfessionalDTO;
import com.pipa.PipaAPI.rest.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassRecordsServiceTest {
    @Mock
    private ClassRecordsRepository classRecordsRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private GoogleDriveMediaService googleDriveMediaService;

    @Mock
    private ProfessionalService professionalService;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private ClassRecordsService classRecordsService;

    @Test
    public void findAllPaginationTest() {
        @SuppressWarnings("unchecked")
        Page<ClassRecords> classRecordsPage = mock(Page.class);
        when(classRecordsRepository.findAll(any(PageRequest.class))).thenReturn(classRecordsPage);

        List<ClassRecordsDTO> result = classRecordsService.findAllPagination(0);

        verify(classRecordsRepository, times(1)).findAll(PageRequest.of(0, 5));

        assertNotNull(result);
    }

    @Test
    public void findByProfessionalCrmPaginationTest() {
        @SuppressWarnings("unchecked")
        Page<ClassRecords> classRecordsPageMock = mock(Page.class);

        when(classRecordsRepository.findClassRecordsByProfessionalCrm(eq("123"), any(Pageable.class))).thenReturn(classRecordsPageMock);

        List<ClassRecords> classRecordsList = new ArrayList<>();

        when(classRecordsPageMock.getContent()).thenReturn(classRecordsList);

        List<ClassRecordsDTO> result = classRecordsService.findClassRecordsByProfessionalCrm(0, "123");

        verify(classRecordsRepository, times(1)).findClassRecordsByProfessionalCrm(eq("123"), any(Pageable.class));

        assertNotNull(result);
    }

    @Test
    public void findByStudentCpfPaginationTest() {
        @SuppressWarnings("unchecked")
        Page<ClassRecords> classRecordsPageMock = mock(Page.class);

        when(classRecordsRepository.findClassRecordsByStudentCpf(eq("12345"), any(Pageable.class))).thenReturn(classRecordsPageMock);

        List<ClassRecords> classRecordsList = new ArrayList<>();

        when(classRecordsPageMock.getContent()).thenReturn(classRecordsList);

        List<ClassRecordsDTO> result = classRecordsService.findClassRecordsByStudentCpf(0, "12345");

        verify(classRecordsRepository, times(1)).findClassRecordsByStudentCpf(eq("12345"), any(Pageable.class));

        assertNotNull(result);
    }

    @Test
    public void saveTest() {
        ClassRecordsDTO dto = new ClassRecordsDTO();
        dto.setId(1L);
        dto.setClassDate(LocalDate.parse("2024-03-03"));
        dto.setStartTime(LocalTime.parse("09:00:00"));
        dto.setEndTime(LocalTime.parse("11:00:00"));
        dto.setSubject("Aula pa cabeça tentativa 2");
        dto.setStatus(StatusClass.PLANNED);
        dto.setLocation("Online");
        dto.setNotes(List.of("notas numero 2", "notas numero 2"));

        CleanProfessionalDTO professionalDTO = new CleanProfessionalDTO();
        professionalDTO.setCrm("string1");
        dto.setProfessional(professionalDTO);

        StudentDTO student = new StudentDTO();
        student.setCpf("string1");
        dto.setStudent(student);

        GoogleDriveMedia googleDriveMedia = new GoogleDriveMedia();
        googleDriveMedia.setId(1L);
        dto.setMedia(googleDriveMedia);

        when(professionalService.findByIdClean(anyString())).thenReturn(professionalDTO);

        ClassRecords classRecordsSaved = ClassRecordsDTO.toOBJ(dto);
        when(classRecordsRepository.save(any(ClassRecords.class))).thenReturn(classRecordsSaved);

        ClassRecordsDTO result = classRecordsService.save(dto);

        verify(classRecordsRepository, times(1)).save(any(ClassRecords.class));

        assertNotNull(result);
        assertEquals(classRecordsSaved, ClassRecordsDTO.toOBJ(result));
    }


    @Test
    public void updateTest() {
        ClassRecordsDTO dto = new ClassRecordsDTO();
        dto.setId(1L);
        dto.setClassDate(LocalDate.parse("2024-03-03"));
        dto.setStartTime(LocalTime.parse("09:00:00"));
        dto.setEndTime(LocalTime.parse("11:00:00"));
        dto.setSubject("Aula pa cabeça tentativa 2");
        dto.setStatus(StatusClass.PLANNED);
        dto.setLocation("Online");
        dto.setNotes(List.of("notas numero 2", "notas numero 2"));

        CleanProfessionalDTO professionalDTO = new CleanProfessionalDTO();
        professionalDTO.setCrm("string1");
        dto.setProfessional(professionalDTO);

        StudentDTO student = new StudentDTO();
        student.setCpf("string1");
        dto.setStudent(student);

        GoogleDriveMedia googleDriveMedia = new GoogleDriveMedia();
        googleDriveMedia.setId(1L);
        dto.setMedia(googleDriveMedia);

        when(professionalService.findByIdClean(anyString())).thenReturn(professionalDTO);

        when(googleDriveMediaService.findById(anyLong())).thenReturn(googleDriveMedia);

        ClassRecords classRecordsSaved = ClassRecordsDTO.toOBJ(dto);
        when(classRecordsRepository.save(any(ClassRecords.class))).thenReturn(classRecordsSaved);

        ClassRecordsDTO result = classRecordsService.update(dto);

        verify(classRecordsRepository, times(1)).save(any(ClassRecords.class));

        assertNotNull(result);
        assertEquals(classRecordsSaved, ClassRecordsDTO.toOBJ(result));
    }


    @Test
    public void testDelete() {
        Long id = 1L;

        when(classRecordsRepository.findById(id)).thenReturn(Optional.of(new ClassRecords()));

        classRecordsService.delete(id);

        verify(classRecordsRepository, times(1)).delete(any(ClassRecords.class));
    }

    @Test
    public void testFindById() {
        Long id = 1L;

        when(classRecordsRepository.findById(id)).thenReturn(Optional.of(new ClassRecords()));

        ClassRecords result = classRecordsService.findById(id);

        assertNotNull(result);
    }

    @Test
    public void findByIdNotFoundExceptionTest() {
        try {
            ClassRecords result = this.classRecordsService.findById(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"The record with id 'null' not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.classRecordsService.findById(null));
        }
    }
}
