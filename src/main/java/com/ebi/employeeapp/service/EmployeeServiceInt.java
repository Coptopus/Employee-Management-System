package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.EmpSaveDTO;
import com.ebi.employeeapp.model.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInt {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    List<EmployeeDTO> getEmployeesByName(String name);
    List<EmployeeDTO> searchEmployeesByName(String name);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmpSaveDTO updateEmployee(EmpSaveDTO empSaveDTO);
    EmpSaveDTO patchEmployee(EmpSaveDTO empSaveDTO);
    boolean deleteEmployee(int id);
}
