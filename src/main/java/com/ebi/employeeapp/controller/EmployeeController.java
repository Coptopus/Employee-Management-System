package com.ebi.employeeapp.controller;

import com.ebi.employeeapp.exceptions.ResourceNotFoundException;
import com.ebi.employeeapp.model.EmpSaveDTO;
import com.ebi.employeeapp.model.EmployeeDTO;
import com.ebi.employeeapp.repo.EmployeeRepository;
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
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        return "index";
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
    public String postForm(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
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

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        EmployeeDTO employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @PostMapping("/update")
    public String postUpdateForm(EmpSaveDTO empSaveDTO) {
        EmpSaveDTO updatedEmployee = employeeService.patchEmployee(empSaveDTO);
        if (updatedEmployee != null) {
            return "redirect:../employees/view";
        } else {
            throw new ResourceNotFoundException("Employee with id " + empSaveDTO.getId_employee() + " not found");
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

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "deleteEmployee";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployeeByIdReturn(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return "redirect:../view";
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }
}
