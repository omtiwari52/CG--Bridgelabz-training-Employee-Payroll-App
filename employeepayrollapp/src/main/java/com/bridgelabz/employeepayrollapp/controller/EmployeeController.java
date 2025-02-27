package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.entities.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired    //dependency injection
    private EmployeeService employeeService;

    @GetMapping      //home page
    public String welcome(){
        log.info("App Started / route.");
        return "Welcome to Employee Payroll App";
    }

    @GetMapping("/all")    //get list of all employees
    public List<EmployeeEntity> getAllEmployees(){
        log.info("Fetching all employees");
        List<EmployeeEntity> employees = employeeService.getAllEmployee();
        log.info("Total employees fetched: {}",employees.size());
        return employees;
    }

    @GetMapping("/{id}")    //get employees by id
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable Long id){
        log.info("Fetching employees with id: {}", id);
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        if(employee.isPresent()){
            log.info("Employee found: {}", employee.get());
        }else {
            log.warn("Employee with id {} not found", id);
        }
        return employee;
    }

    @PostMapping("/add")    //add new employee to the application
    public EmployeeEntity addNewEmployee(@Valid @RequestBody EmployeeEntity employee) {
        log.info("Adding new employee: {}", employee);
        EmployeeEntity employee1 = employeeService.saveEmployee(employee);
        log.info("Employee added successfully with id: {}", employee1.getId());
        return employee1;
    }


    @PutMapping("/update/details/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeDetails(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeEntity updatedEmployee) {
        log.info("Updating details of employee with id: {}", id);
        EmployeeEntity employee = employeeService.updateEmployee(id, updatedEmployee);
        log.info("Details of employee updated: {}", employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        log.info("Deleting user with id: {}", id);
        if(employeeService.deleteEmployee(id)){
            log.info("Employee with ID {} deleted successfully.", id);
            return "Employee deleted successfully";
        }
        log.warn("Employee with ID {} not found.", id);
        return "Employee Not Found";
    }
}