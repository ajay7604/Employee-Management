package com.nexdew.Employee_Management.repository;

import com.nexdew.Employee_Management.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary ,Long> {

}
