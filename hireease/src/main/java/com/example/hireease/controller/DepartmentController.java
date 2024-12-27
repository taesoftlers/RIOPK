package com.example.hireease.controller;

import com.example.hireease.model.Candidate;
import com.example.hireease.model.Department;
import com.example.hireease.model.Employee;
import com.example.hireease.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PatchMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment;
        try {
            updatedDepartment = departmentService.updateDepartment(id, department);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id) {
        Department deletedDepartment;
        try {
            deletedDepartment = departmentService.deleteDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedDepartment, HttpStatus.OK);
    }

    @GetMapping("/department/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/candidates")
    public ResponseEntity<List<Candidate>> getCandidatesByDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.getCandidates(), HttpStatus.OK);
    }

}
