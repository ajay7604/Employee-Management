package com.nexdew.Employee_Management.controller;


import com.nexdew.Employee_Management.entity.Department;
import com.nexdew.Employee_Management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
    @Autowired
    private  DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
    Department dept= departmentService.createDepartment(department);
   return new ResponseEntity<>(dept, HttpStatus.CREATED);
    }

    @GetMapping("/{deptID}")
    public ResponseEntity<Department> getDepartmenteById(@PathVariable Long deptID){
        return ResponseEntity.ok(departmentService.getById(deptID));
    }

    @DeleteMapping ("/{deptID}")
    public ResponseEntity<Boolean> DeleteById(@PathVariable Long deptID){
        return ResponseEntity.ok(departmentService.deleteById(deptID));
    }

    @PutMapping("{deptID}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long deptID , @RequestBody Department updatedDepartment){
        return  ResponseEntity.ok(departmentService.updatedDepartment(deptID,updatedDepartment));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Department>> getallDepartment(){
        return ResponseEntity.ok(departmentService.getallDepartment());
    }
    @PatchMapping("/{deptId}")
    public ResponseEntity<Department> PartialUpdates(@PathVariable Long deptId, @RequestBody Map<String ,Object> updatedListDepartment){
        return new  ResponseEntity<>(departmentService.getPartialUpdatesOfDepartment(deptId,updatedListDepartment),HttpStatus.CREATED);
    }

}
