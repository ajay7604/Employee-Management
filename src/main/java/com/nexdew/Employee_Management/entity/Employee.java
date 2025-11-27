package com.nexdew.Employee_Management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
      //  @JsonIgnore
        private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
  // @JsonIgnore
    @JsonManagedReference
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
   // @JsonIgnore
    private List<Project> projects = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "salary_id")
//    @JsonManagedReference
//    private Salary salary;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Salary salary;




}
