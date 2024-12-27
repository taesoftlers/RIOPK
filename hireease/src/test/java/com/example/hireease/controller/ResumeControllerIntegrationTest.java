//package com.example.hireease.controller;
//
//import com.example.hireease.model.Resume;
//import com.example.hireease.repository.ResumeRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ResumeControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ResumeRepository resumeRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setup() {
//        resumeRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveResume() throws Exception {
//        Resume resume = new Resume();
//        resume.setResumeFile(null); // Example: You can replace null with a Blob for actual testing
//
//        mockMvc.perform(post("/api/v1/resume")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(resume)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.resumeID", notNullValue()));
//    }
//
//    @Test
//    public void testGetResume() throws Exception {
//        Resume resume = new Resume();
//        resume.setResumeFile(null);
//        Resume savedResume = resumeRepository.save(resume);
//
//        mockMvc.perform(get("/api/v1/resume/" + savedResume.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resumeID", is(savedResume.getId().intValue())));
//    }
//
//    @Test
//    public void testGetResumes() throws Exception {
//        Resume resume1 = new Resume();
//        resume1.setResumeFile(null);
//
//        Resume resume2 = new Resume();
//        resume2.setResumeFile(null);
//
//        resumeRepository.save(resume1);
//        resumeRepository.save(resume2);
//
//        mockMvc.perform(get("/api/v1/resumes"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    public void testUpdateResume() throws Exception {
//        Resume resume = new Resume();
//        resume.setResumeFile(null);
//        Resume savedResume = resumeRepository.save(resume);
//
//        Resume updatedResume = new Resume();
//        updatedResume.setResumeFile(null); // Update with new values
//
//        mockMvc.perform(patch("/api/v1/resume/" + savedResume.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedResume)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resumeID", is(savedResume.getId().intValue())));
//    }
//
//    @Test
//    public void testDeleteResume() throws Exception {
//        Resume resume = new Resume();
//        resume.setResumeFile(null);
//        Resume savedResume = resumeRepository.save(resume);
//
//        mockMvc.perform(delete("/api/v1/resume/" + savedResume.getId()))
//                .andExpect(status().isOk());
//
//        Optional<Resume> deletedResume = resumeRepository.findById(savedResume.getId());
//        assert (deletedResume.isEmpty());
//    }
//}
