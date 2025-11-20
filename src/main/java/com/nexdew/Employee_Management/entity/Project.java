package com.nexdew.Employee_Management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String name;
    private String description;


    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn( name = "department_id")
    private Department department;


}
