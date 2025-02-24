package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.entities.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //method to find all employees
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //method to find employee by ID
    public Optional<EmployeeEntity> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    //method to save new employee to database
    public EmployeeEntity saveEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    //method to update employee details
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee){
        Optional<EmployeeEntity> existingEmp = getEmployeeById(id);
        if(existingEmp.isPresent()){
            EmployeeEntity employee1 = existingEmp.get();
            employee1.setName(employee.getName());
            employee1.setDepartment(employee.getDepartment());
            employee1.setSalary(employee.getSalary());
            return employeeRepository.save(employee1);
        }
        return null;
    }

    //method to delete employee
    public boolean deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
