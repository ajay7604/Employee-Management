package com.nexdew.Employee_Management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    private Double basicSalary;
    private Double hra;
    private Double allowances;

//    @OneToOne(mappedBy = "salary")
//    @JsonBackReference
//    private Employee employee;

    @OneToOne
    @JoinColumn(name = "employee_id")  // FK goes here
    @JsonBackReference
    private Employee employee;


}
