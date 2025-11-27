package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Salary;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Map;

public interface SalaryService {

    Salary createSalaryOfEmployee(Long empId, Salary salary);

    Salary getSalaryById(Long empId);

    Salary getupdatedSalary(Long empId, Salary updatedSalary);


    Boolean deleteSalary(Long empId);

    List<Salary> getAllSalaries();


}
