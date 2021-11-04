package com.javalearner.employeemanagementsystem.studentmanagement.Repository;

import com.javalearner.employeemanagementsystem.studentmanagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
