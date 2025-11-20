package com.nexdew.Employee_Management.controller;

import com.nexdew.Employee_Management.entity.Salary;
import com.nexdew.Employee_Management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/{empId}/salary")
    public ResponseEntity<Salary> createSalary(@PathVariable Long empId , @RequestBody Salary salary){
     Salary assignedsalary = salaryService.createSalaryOfEmployee( empId , salary);
     return new ResponseEntity<>(assignedsalary, HttpStatus.CREATED);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Salary> getSalary(@PathVariable Long empId){
      Salary sal=  salaryService.getSalaryById(empId);
      return  ResponseEntity.ok(sal);
    }

    @PutMapping("/{empId}/update")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long empId , @RequestBody Salary updatedSalary){
     return ResponseEntity.ok( salaryService.getupdatedSalary(empId,updatedSalary));
    }

    @DeleteMapping("{empId}/salary")
    public ResponseEntity<Boolean> deleteSalary(@PathVariable Long empId) {
        return ResponseEntity.ok( salaryService.deleteSalary(empId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Salary>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.getAllSalaries());
    }





}
