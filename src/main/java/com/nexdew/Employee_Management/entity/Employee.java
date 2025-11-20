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
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long employeeId;

        private String name;
        private String email;
        private Long phoneNumber;

        @ManyToOne
        @JoinColumn(name = "deptId")
        private Department department;

        @OneToOne(mappedBy = "employee")
        private Address address;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();


    @OneToOne(mappedBy = "employee")
        private Salary salary;


    }
