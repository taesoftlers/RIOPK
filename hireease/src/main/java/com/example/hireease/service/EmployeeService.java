package com.example.hireease.service;

import com.example.hireease.model.Employee;
import com.example.hireease.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setContactInfo(employee.getContactInfo());
        existingEmployee.setCandidates(employee.getCandidates());
        existingEmployee.setInterviews(employee.getInterviews());
//        existingEmployee.setResumes(employee.getResumes());
        existingEmployee.setVacancies(employee.getVacancies());
        return employeeRepository.save(existingEmployee);
    }

    public Employee deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.deleteById(id);
        return existingEmployee;
    }

}
