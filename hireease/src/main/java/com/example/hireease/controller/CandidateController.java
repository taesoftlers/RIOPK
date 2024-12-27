package com.example.hireease.controller;

import com.example.hireease.model.Candidate;
import com.example.hireease.model.Interview;
import com.example.hireease.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> saveCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id) {
        Candidate candidate;
        try {
            candidate = candidateService.getCandidate(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }

    @PatchMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate;
        try {
            updatedCandidate = candidateService.updateCandidate(id, candidate);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<Candidate> deleteCandidate(@PathVariable Long id) {
        Candidate deletedCandidate;
        try {
            deletedCandidate = candidateService.deleteCandidate(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedCandidate, HttpStatus.OK);
    }

//    @GetMapping("/candidate/{id}/resume")
//    public ResponseEntity<Resume> getResumeByCandidate(@PathVariable Long id) {
//        Candidate candidate;
//        try {
//            candidate = candidateService.getCandidate(id);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(candidate.getResume(), HttpStatus.OK);
//    }

    @GetMapping("candidate/{id}/interviews")
    public ResponseEntity<List<Interview>> getInterviewsByCandidate(@PathVariable Long id) {
        Candidate candidate;
        try {
            candidate = candidateService.getCandidate(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candidate.getInterviews(), HttpStatus.OK);
    }

}
