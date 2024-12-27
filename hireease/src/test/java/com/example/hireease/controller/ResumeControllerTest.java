//package com.example.hireease.controller;
//
//import com.example.hireease.model.Resume;
//import com.example.hireease.service.ResumeService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.sql.Blob;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class ResumeControllerTest {
//
//    @Mock
//    private ResumeService resumeService;
//
//    @InjectMocks
//    private ResumeController resumeController;
//
//    public ResumeControllerTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void saveResume_ShouldReturnCreatedResume() throws Exception {
//        Resume resume = new Resume();
//        resume.setId(1L);
//        resume.setResumeFile(mock(Blob.class));
//
//        when(resumeService.saveResume(resume)).thenReturn(resume);
//
//        ResponseEntity<Resume> response = resumeController.saveResume(resume);
//
//        assertEquals(201, response.getStatusCodeValue());
//        assertEquals(resume, response.getBody());
//        verify(resumeService, times(1)).saveResume(resume);
//    }
//
//    @Test
//    void getResume_ShouldReturnResumeById() {
//        Long resumeId = 1L;
//        Resume resume = new Resume();
//        resume.setId(resumeId);
//
//        when(resumeService.getResume(resumeId)).thenReturn(resume);
//
//        ResponseEntity<Resume> response = resumeController.getResume(resumeId);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(resume, response.getBody());
//        verify(resumeService, times(1)).getResume(resumeId);
//    }
//
//    @Test
//    void getResumes_ShouldReturnListOfResumes() {
//        Resume resume1 = new Resume();
//        resume1.setId(1L);
//        Resume resume2 = new Resume();
//        resume2.setId(2L);
//
//        List<Resume> resumes = Arrays.asList(resume1, resume2);
//
//        when(resumeService.getResumes()).thenReturn(resumes);
//
//        List<Resume> response = resumeController.getResumes();
//
//        assertEquals(2, response.size());
//        assertEquals(resumes, response);
//        verify(resumeService, times(1)).getResumes();
//    }
//
//    @Test
//    void updateResume_ShouldReturnUpdatedResume() {
//        Long resumeId = 1L;
//        Resume resume = new Resume();
//        resume.setId(resumeId);
//
//        when(resumeService.updateResume(eq(resumeId), any(Resume.class))).thenReturn(resume);
//
//        ResponseEntity<Resume> response = resumeController.updateResume(resumeId, resume);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(resume, response.getBody());
//        verify(resumeService, times(1)).updateResume(resumeId, resume);
//    }
//
//    @Test
//    void deleteResume_ShouldReturnDeletedResume() {
//        Long resumeId = 1L;
//        Resume resume = new Resume();
//        resume.setId(resumeId);
//
//        when(resumeService.deleteResume(resumeId)).thenReturn(resume);
//
//        ResponseEntity<Resume> response = resumeController.deleteResume(resumeId);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(resume, response.getBody());
//        verify(resumeService, times(1)).deleteResume(resumeId);
//    }
//}
