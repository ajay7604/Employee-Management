package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Department;
import com.nexdew.Employee_Management.repository.DepartmentRepository;
import com.nexdew.Employee_Management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl  implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department) ;
    }

    @Override
    public Department getById(Long deptID) {
        return departmentRepository.findById(deptID)
                .orElseThrow(() -> new IllegalArgumentException("Department not Found:"));
    }

    @Override
    public Boolean deleteById(Long deptID) {
        departmentRepository.findById(deptID)
                .orElseThrow(()-> new RuntimeException("department not found by this id"));
        departmentRepository.deleteById(deptID);
     return true;

    }

    @Override
    public Department updatedDepartment(Long deptID, Department updatedDepartment) {
        Department existingDept = departmentRepository.findById(deptID)
                .orElseThrow(() -> new RuntimeException("department not found by this id"));
         existingDept.setName(updatedDepartment.getName());
         existingDept.setDeptId(updatedDepartment.getDeptId());

         return departmentRepository.save(existingDept);
    }

    @Override
    public List<Department> getallDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getPartialUpdatesOfDepartment(Long deptId, Map<String, Object> updatedListDepartment) {
        Department existingDepartment = departmentRepository.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found " + deptId));
        updatedListDepartment.forEach((field, value) ->{
            switch (field){
                case "name":existingDepartment.setName(value.toString());break;
                default: throw new IllegalArgumentException("field not found");
            }
        } );
        return departmentRepository.save(existingDepartment);
    }
}
