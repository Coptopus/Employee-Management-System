package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.Employee;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).orElse(null);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employee = employeeRepository.save(employee);
            return modelMapper.map(employee, EmployeeDTO.class);
        }
        return null;
    }

    public EmployeeDTO patchEmployee(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            if (employeeDTO.getName() != null) {
                existingEmployee.setName(employeeDTO.getName());
            }
            if (employeeDTO.getSalary() != 0){
                existingEmployee.setSalary(employeeDTO.getSalary());
            }
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return modelMapper.map(updatedEmployee, EmployeeDTO.class);
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
