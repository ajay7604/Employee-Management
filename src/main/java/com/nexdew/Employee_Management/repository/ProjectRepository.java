package com.nexdew.Employee_Management.repository;

import com.nexdew.Employee_Management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
