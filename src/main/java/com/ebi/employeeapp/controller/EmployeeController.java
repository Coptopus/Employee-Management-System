package com.ebi.employeeapp.controller;

import com.ebi.employeeapp.model.Employee;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
