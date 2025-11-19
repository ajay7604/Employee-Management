package com.nexdew.Employee_Management.controller;


import com.nexdew.Employee_Management.entity.Project;
import com.nexdew.Employee_Management.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("/create-project")
    public ResponseEntity<Project> createProject(@RequestBody Project project ){
        Project projects =  projectService.createProject(project);
        return new ResponseEntity<>(projects, HttpStatus.CREATED);

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getByProjectById(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.getById(projectId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Boolean> deleteProjectById(@PathVariable Long projectId){
        return new ResponseEntity<>(projectService.deleteProjectById(projectId), HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Project> updateProject(@PathVariable  Long projectId, @RequestBody Project updatedProject) {
        return ResponseEntity.ok(projectService.updateproject(projectId,updatedProject));
    }
}


