package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Project;
import com.nexdew.Employee_Management.repository.ProjectRepository;
import com.nexdew.Employee_Management.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

@Autowired
private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long projectId) {
      return projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project id not found:"+projectId));

    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }


    @Override
    public Boolean deleteProjectById(Long projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project id not found:"+projectId));
        projectRepository.deleteById(projectId);
        return true;
    }

    @Override
    public Project updateproject(Long projectId, Project updatedProject) {
        Project Existingproject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("project id not found:" + projectId));

        Existingproject.setProjectId(updatedProject.getProjectId());
        Existingproject.setName(updatedProject.getName());
        Existingproject.setDescription(updatedProject.getDescription());

        return projectRepository.save(Existingproject);
    }
}
