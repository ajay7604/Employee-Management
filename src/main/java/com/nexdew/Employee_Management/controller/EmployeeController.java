package com.nexdew.Employee_Management.controller;


import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;

    @PostMapping("/create-employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody  Employee employee ){
        Employee employees = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employees,HttpStatus.CREATED);

    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getByEmployeeById(@PathVariable Long empId){
        return ResponseEntity.ok(employeeService.getById(empId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long empId){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(empId), HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable  Long empId, @RequestBody Employee updatedEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(empId,updatedEmployee));
    }


    //it is for partial update in employee
    @PatchMapping("/{empId}")
    public ResponseEntity<Employee> updatePartialEmployee(@PathVariable Long empId,@RequestBody Map<String,Object> updatedList){
        return new ResponseEntity<>(employeeService.updatePartialEmployee(empId,updatedList), HttpStatus.ACCEPTED);
    }

}
