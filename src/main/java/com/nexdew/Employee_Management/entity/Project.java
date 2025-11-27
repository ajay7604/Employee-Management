package com.nexdew.Employee_Management.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;


}
