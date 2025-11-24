package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Department;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Department createDepartment(Department department);

    Department getById(Long deptID);

    Boolean deleteById(Long deptID);

    Department updatedDepartment(Long deptID, Department updatedDepartment);

    List<Department> getallDepartment();

    Department getPartialUpdatesOfDepartment(Long deptId, Map<String, Object> updatedListDepartment);
}
