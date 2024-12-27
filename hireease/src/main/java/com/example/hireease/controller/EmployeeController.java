package com.example.hireease.controller;

import com.example.hireease.model.*;
import com.example.hireease.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee;
        try {
            updatedEmployee = employeeService.updateEmployee(id, employee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        Employee deletedEmployee;
        try {
            deletedEmployee = employeeService.deleteEmployee(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}/candidates")
    public ResponseEntity<List<Candidate>> getCandidatesByEmployee(@PathVariable Long id) {
        Employee employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.getCandidates(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}/interviews")
    public ResponseEntity<List<Interview>> getInterviewsByEmployee(@PathVariable Long id) {
        Employee employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.getInterviews(), HttpStatus.OK);
    }

//    @GetMapping("/employee/{id}/resumes")
//    public ResponseEntity<List<Resume>> getResumesByEmployee(@PathVariable Long id) {
//        Employee employee;
//        try {
//            employee = employeeService.getEmployee(id);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(employee.getResumes(), HttpStatus.OK);
//    }

    @GetMapping("/employee/{id}/vacancies")
    public ResponseEntity<List<Vacancy>> getVacanciesByEmployee(@PathVariable Long id) {
        Employee employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.getVacancies(), HttpStatus.OK);
    }

}
