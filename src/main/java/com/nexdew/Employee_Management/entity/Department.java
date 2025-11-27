package com.nexdew.Employee_Management.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deptId")
    private Long deptId;

    private String name;


    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Project> projects;


}
