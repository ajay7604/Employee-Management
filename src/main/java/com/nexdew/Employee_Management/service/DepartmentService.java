package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Department;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    Department getById(Long deptID);

    Boolean deleteById(Long deptID);

    Department updatedDepartment(Long deptID, Department updatedDepartment);

    List<Department> getallDepartment();
}
