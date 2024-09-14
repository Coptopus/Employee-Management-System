package com.ebi.employeeapp.controller;

import com.ebi.employeeapp.exceptions.ResourceNotFoundException;
import com.ebi.employeeapp.model.EmpSaveDTO;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/view")
    public String getAllEmployeesView(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }

    @GetMapping("/findBy")
    @ResponseBody
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/addEmployee")
    public String getForm(Model model){
        model.addAttribute("employee", new EmployeeDTO());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String postForm(@ModelAttribute("employee") EmployeeDTO employeeDTO, Model model) {
        employeeService.createEmployee(employeeDTO);
        return "redirect:../employees/view";
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<EmpSaveDTO> updateEmployee(@RequestBody EmpSaveDTO empSaveDTO) {
        EmpSaveDTO updatedEmployee = employeeService.updateEmployee(empSaveDTO);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<EmpSaveDTO> patchEmployee(@RequestBody EmpSaveDTO empSaveDTO) {
        EmpSaveDTO updatedEmployee = employeeService.patchEmployee(empSaveDTO);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
