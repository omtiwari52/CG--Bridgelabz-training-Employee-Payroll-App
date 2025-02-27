package com.bridgelabz.employeepayrollapp.repositories;

import com.bridgelabz.employeepayrollapp.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//using repository to use predefined methods and store data in a database
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
