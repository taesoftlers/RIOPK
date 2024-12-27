//package com.example.hireease.controller;
//
//import com.example.hireease.model.Resume;
//import com.example.hireease.service.ResumeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ResumeController {
//
//    @Autowired
//    private ResumeService resumeService;
//
////    @PostMapping("/resume")
////    public ResponseEntity<Resume> saveResume(@RequestBody Resume resume) {
////        Resume savedResume = resumeService.saveResume(resume);
////        return new ResponseEntity<>(savedResume, HttpStatus.CREATED);
////    }
//
//    @DeleteMapping("/resume/{id}")
//    public ResponseEntity<Resume> deleteResume(@PathVariable Long id) {
//        Resume deletedResume;
//        try {
//            deletedResume = resumeService.deleteResume(id);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(deletedResume, HttpStatus.OK);
//    }
//
//}
