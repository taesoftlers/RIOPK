package com.example.hireease.controller;

import com.example.hireease.model.Interview;
import com.example.hireease.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/interview")
    public ResponseEntity<Interview> saveInterview(@RequestBody Interview interview) {
        Interview savedInterview = interviewService.saveInterview(interview);
        return new ResponseEntity<>(savedInterview, HttpStatus.CREATED);
    }

    @GetMapping("/interview/{id}")
    public ResponseEntity<Interview> getInterview(@PathVariable Long id) {
        Interview interview;
        try {
            interview = interviewService.getInterview(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @GetMapping("/interviews")
    public List<Interview> getInterviews() {
        return interviewService.getInterviews();
    }

    @PatchMapping("/interview/{id}")
    public ResponseEntity<Interview> updateInterview(@PathVariable Long id, @RequestBody Interview interview) {
        Interview updatedInterview;
        try {
            updatedInterview = interviewService.updateInterview(id, interview);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedInterview, HttpStatus.OK);
    }

    @DeleteMapping("/interview/{id}")
    public ResponseEntity<Interview> deleteInterview(@PathVariable Long id) {
        Interview deletedInterview;
        try {
            deletedInterview = interviewService.deleteInterview(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedInterview, HttpStatus.OK);
    }

}
