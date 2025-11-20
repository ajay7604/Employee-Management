package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.entity.Salary;
import com.nexdew.Employee_Management.repository.EmployeeRepository;
import com.nexdew.Employee_Management.repository.SalaryRepository;
import com.nexdew.Employee_Management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Salary createSalaryOfEmployee(Long empId, Salary salary) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee Not Found BY this id:" + empId));

     salary.setEmployee(employee);
     return salaryRepository.save(salary);
    }

    @Override
    public Salary getSalaryById(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee ID not found:" + empId));

        Salary salary = employee.getSalary();
        if (salary == null) {
            throw new RuntimeException("Salary not found for employee id: " + empId);
        }
        return salary;

    }

    @Override
    public Salary getupdatedSalary(Long empId, Salary updatedSalary) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found for this given id:"+empId));

        Salary existingSalary = employee.getSalary();
        if (existingSalary==null){
            throw new IllegalArgumentException(" existing salary is null");
        }

        existingSalary.setBasicSalary(updatedSalary.getBasicSalary());
        existingSalary.setAllowances(updatedSalary.getAllowances());
        existingSalary.setHra(existingSalary.getHra());

        return salaryRepository.save(existingSalary);

    }

    @Override
    public Boolean deleteSalary(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee ID Not Found"));

        Salary salary = employee.getSalary();
        if (salary==null){
            throw new RuntimeException("Salary of this Employee is NULL:");
        }
        employee.setSalary(null);
        employeeRepository.save(employee);

        salaryRepository.delete(salary);
        return true;
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
}
