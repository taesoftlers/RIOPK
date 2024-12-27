package com.example.hireease.service;

import com.example.hireease.model.Vacancy;
import com.example.hireease.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy saveVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    public Vacancy getVacancy(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }

    public Vacancy updateVacancy(Long id, Vacancy vacancy) {
        Vacancy existingVacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        existingVacancy.setJobTitle(vacancy.getJobTitle());
        existingVacancy.setJobDescription(vacancy.getJobDescription());
        existingVacancy.setWorkExperience(vacancy.getWorkExperience());
        existingVacancy.setEducation(vacancy.getEducation());
        existingVacancy.setSkills(vacancy.getSkills());
        existingVacancy.setLanguage(vacancy.getLanguage());
        return vacancyRepository.save(existingVacancy);
    }

    public Vacancy deleteVacancy(Long id) {
        Vacancy existingVacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        vacancyRepository.deleteById(id);
        return existingVacancy;
    }

}
