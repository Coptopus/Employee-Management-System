package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.Employee;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).orElse(null);
    }

}
