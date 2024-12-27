package com.example.hireease.controller;

import com.example.hireease.model.Vacancy;
import com.example.hireease.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/vacancy")
    public ResponseEntity<Vacancy> saveVacancy(@RequestBody Vacancy vacancy) {
        Vacancy savedVacancy = vacancyService.saveVacancy(vacancy);
        return new ResponseEntity<>(savedVacancy, HttpStatus.CREATED);
    }

    @GetMapping("/vacancy/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable Long id) {
        Vacancy vacancy;
        try {
            vacancy = vacancyService.getVacancy(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vacancy, HttpStatus.OK);
    }

    @GetMapping("/vacancies")
    public List<Vacancy> getVacancies() {
        return vacancyService.getVacancies();
    }

    @PatchMapping("/vacancy/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancy) {
        Vacancy updatedVacancy;
        try {
            updatedVacancy = vacancyService.updateVacancy(id, vacancy);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedVacancy, HttpStatus.OK);
    }

    @DeleteMapping("/vacancy/{id}")
    public ResponseEntity<Vacancy> deleteVacancy(@PathVariable Long id) {
        Vacancy deletedVacancy;
        try {
            deletedVacancy = vacancyService.deleteVacancy(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedVacancy, HttpStatus.OK);
    }

}
