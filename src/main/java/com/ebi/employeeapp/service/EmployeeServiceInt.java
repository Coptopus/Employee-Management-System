package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.Employee;
import com.ebi.employeeapp.model.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInt {
    public List<EmployeeDTO> getAllEmployees();
    public EmployeeDTO getEmployeeById(int id);
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO);
    public EmployeeDTO patchEmployee(int id, EmployeeDTO employeeDTO);
    public boolean deleteEmployee(int id);
}
