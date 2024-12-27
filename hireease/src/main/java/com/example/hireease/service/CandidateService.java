package com.example.hireease.service;

import com.example.hireease.model.Candidate;
import com.example.hireease.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate getCandidate(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        existingCandidate.setFullName(candidate.getFullName());
        existingCandidate.setPhone(candidate.getPhone());
        existingCandidate.setEmail(candidate.getEmail());
        existingCandidate.setAddress(candidate.getAddress());
//        existingCandidate.setResume(candidate.getResume());
        existingCandidate.setInterviews(candidate.getInterviews());
        return candidateRepository.save(existingCandidate);
    }

    public Candidate deleteCandidate(Long id) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        candidateRepository.deleteById(id);
        return existingCandidate;
    }

}
