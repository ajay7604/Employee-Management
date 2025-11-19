package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.repository.SalaryRepository;
import com.nexdew.Employee_Management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
}
