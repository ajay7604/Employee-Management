package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Address;
import com.nexdew.Employee_Management.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    Project getById(Long projectId);

    List<Project> getAllProject();

    Boolean deleteProjectById(Long projectId);

    Project updateproject(Long projectId, Project updatedProject);
}
