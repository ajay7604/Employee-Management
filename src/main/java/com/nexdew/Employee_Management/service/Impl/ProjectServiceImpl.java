package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Department;
import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.entity.Project;
import com.nexdew.Employee_Management.repository.DepartmentRepository;
import com.nexdew.Employee_Management.repository.EmployeeRepository;
import com.nexdew.Employee_Management.repository.ProjectRepository;
import com.nexdew.Employee_Management.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

@Autowired
private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Project createProject(Project project) {

        //  Set Department
        if (project.getDepartment() != null ) {
            Long deptId = project.getDepartment().getDeptId();
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            project.setDepartment(department);
        }

        //  Set Employees
        if (project.getEmployees() != null) {

            List<Employee> finalEmployees = new ArrayList<>();

            for (Employee e : project.getEmployees()) {
                Employee empDb = employeeRepository.findById(e.getEmployeeId())
                        .orElseThrow(() -> new RuntimeException("Employee not found"));
                finalEmployees.add(empDb);
            }

            project.setEmployees(finalEmployees);
        }

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

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("project id not found:" + projectId));

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());

        // ---- Update Department ----
        if (updatedProject.getDepartment() != null) {
            Long deptId = updatedProject.getDepartment().getDeptId();

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            existingProject.setDepartment(department);
        }

        // ---- Update Employees ----
        if (updatedProject.getEmployees() != null) {

            List<Employee> finalEmployees = new ArrayList<>();

            for (Employee e : updatedProject.getEmployees()) {
                Employee empDb = employeeRepository.findById(e.getEmployeeId())
                        .orElseThrow(() -> new RuntimeException("Employee not found"));
                finalEmployees.add(empDb);
            }

            existingProject.setEmployees(finalEmployees);
        }

        return projectRepository.save(existingProject);
    }

    @Override
    public Project getPartialUpdate(Long projectId, Map<String, Object> updatedList) {

        Project existedProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("project ID not found:" + projectId));

        updatedList.forEach((field, value) -> {

            switch (field) {

                case "name":
                    existedProject.setName(value.toString());
                    break;

                case "description":
                    existedProject.setDescription(value.toString());
                    break;

                case "departmentId": {
                    Long deptId = Long.valueOf(value.toString());
                    Department department = departmentRepository.findById(deptId)
                            .orElseThrow(() -> new RuntimeException("Department not found"));
                    existedProject.setDepartment(department);
                    break;
                }

                default:
                    throw new IllegalArgumentException("Field not supported: " + field);
            }
        });

        return projectRepository.save(existedProject);
    }

}
