package com.example.hireease.service;

import com.example.hireease.model.Interview;
import com.example.hireease.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    public List<Interview> getInterviews() {
        return interviewRepository.findAll();
    }

    public Interview saveInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    public Interview getInterview(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
    }

    public Interview updateInterview(Long id, Interview interview) {
        Interview existingInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        existingInterview.setInterviewName(interview.getInterviewName());
        existingInterview.setType(interview.getType());
        existingInterview.setReport(interview.getReport());
        return interviewRepository.save(existingInterview);
    }

    public Interview deleteInterview(Long id) {
        Interview existingInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        interviewRepository.deleteById(id);
        return existingInterview;
    }

}
