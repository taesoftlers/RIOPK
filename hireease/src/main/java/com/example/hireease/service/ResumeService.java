//package com.example.hireease.service;
//
//import com.example.hireease.model.Resume;
//import com.example.hireease.repository.ResumeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResumeService {
//
//    @Autowired
//    private ResumeRepository resumeRepository;
//
//    public Resume saveResume(Resume resume) {
//        return resumeRepository.save(resume);
//    }
//
//    public Resume deleteResume(Long id) {
//        Resume existingResume = resumeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Resume not found"));
//        resumeRepository.deleteById(id);
//        return existingResume;
//    }
//
//}
