package com.nexdew.Employee_Management.entity;


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

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
