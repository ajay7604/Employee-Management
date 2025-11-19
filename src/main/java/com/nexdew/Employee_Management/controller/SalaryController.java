package com.nexdew.Employee_Management.controller;

import com.nexdew.Employee_Management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;


}
