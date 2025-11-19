package com.nexdew.Employee_Management.repository;

import com.nexdew.Employee_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
