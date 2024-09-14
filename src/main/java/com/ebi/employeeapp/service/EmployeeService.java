package com.ebi.employeeapp.service;

import com.ebi.employeeapp.entity.Employee;
import com.ebi.employeeapp.model.EmpSaveDTO;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInt{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).orElse(null);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByName(String name){
        return employeeRepository.findByName(name).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmpSaveDTO updateEmployee(EmpSaveDTO empSaveDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empSaveDTO.getId_employee());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(empSaveDTO.getName());
            employee.setSalary(empSaveDTO.getSalary());
            employee = employeeRepository.save(employee);
            return modelMapper.map(employee, EmpSaveDTO.class);
        }
        return null;
    }

    @Override
    public EmpSaveDTO patchEmployee(EmpSaveDTO empSaveDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empSaveDTO.getId_employee());
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            if (empSaveDTO.getName() != null) {
                existingEmployee.setName(empSaveDTO.getName());
            }
            if (empSaveDTO.getSalary() <= 0){
                throw new IllegalArgumentException("Salary should be greater than 0");
            }
            if (!Double.isNaN(empSaveDTO.getSalary())){
                existingEmployee.setSalary(empSaveDTO.getSalary());
            }
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return modelMapper.map(updatedEmployee, EmpSaveDTO.class);
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
