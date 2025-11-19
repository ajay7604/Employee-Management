package com.nexdew.Employee_Management.repository;

import com.nexdew.Employee_Management.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary ,Long> {
}
