package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Employee;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getById(Long empId);


    Employee updatePartialEmployee(Long empId, Map<String, Object> updatedList);

    List<Employee> getAllEmployees();

    Boolean deleteEmployeeById(Long empId);

    Employee updateEmployee(Long empId, Employee updatedEmployee);
}
