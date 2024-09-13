package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.EmpSaveDTO;
import com.ebi.employeeapp.model.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInt {
    public List<EmployeeDTO> getAllEmployees();
    public EmployeeDTO getEmployeeById(int id);
    public List<EmployeeDTO> getEmployeesByName(String name);
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    public EmpSaveDTO updateEmployee(EmpSaveDTO empSaveDTO);
    public EmpSaveDTO patchEmployee(EmpSaveDTO empSaveDTO);
    public boolean deleteEmployee(int id);
}
