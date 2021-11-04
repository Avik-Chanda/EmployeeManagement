package com.javalearner.employeemanagementsystem.studentmanagement.Controller;

import com.javalearner.employeemanagementsystem.studentmanagement.Exception.ResourceNotFoundException;
import com.javalearner.employeemanagementsystem.studentmanagement.Model.Employee;
import com.javalearner.employeemanagementsystem.studentmanagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    //get employees
    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    //get Employess by Id
    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Employee employee = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with this id :" + id));
        return ResponseEntity.ok().body(employee);
    }

    //save Employee
    @PostMapping("employee")
    public Employee saveEmployee(@RequestBody Employee request) {
        return repository.save(request);
    }

    //update Employee
    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @Validated @RequestBody Employee request) throws ResourceNotFoundException {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id :" + request.getId()));
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmail(request.getEmail());
        return ResponseEntity.ok(repository.save(employee));
    }

    //delete employee
    @DeleteMapping("employee/{id}")
    public Map<String, Boolean> deleteEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Employee employee = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with this id :" + id));
        repository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
