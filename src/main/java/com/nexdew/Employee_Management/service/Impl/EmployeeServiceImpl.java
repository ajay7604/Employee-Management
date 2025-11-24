package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.*;
import com.nexdew.Employee_Management.repository.*;
import com.nexdew.Employee_Management.service.DepartmentService;
import com.nexdew.Employee_Management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;
    private final SalaryRepository salaryRepository;
    private final ProjectRepository projectRepository;
    private final AddressRepository addressRepository;


    @Override
    public Employee createEmployee(Employee employee) {
      return  employeeRepository.save(employee);

    }

    @Override
    public Employee getById(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not Found:"));
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee updateEmployee(Long empId, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + empId));

        // Update basic fields
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployee.getEmail());

        // ---------- Update Department ----------
        if (updatedEmployee.getDepartment() != null) {

            Long deptId = updatedEmployee.getDepartment().getDeptId();

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            existingEmployee.setDepartment(department);
        }


        // ---------- Update Address ----------
        if (updatedEmployee.getAddress() != null) {

            Address existingAddress = existingEmployee.getAddress();

            if (existingAddress == null) {
                existingAddress = new Address();
            }

            existingAddress.setStreet(updatedEmployee.getAddress().getStreet());
            existingAddress.setState(updatedEmployee.getAddress().getState());
            existingAddress.setCity(updatedEmployee.getAddress().getCity());
            existingAddress.setEmployee(existingEmployee);

            existingEmployee.setAddress(existingAddress);
        }



        // ---------- Update Salary ----------
        if (updatedEmployee.getSalary() != null) {

            Salary existingSalary = existingEmployee.getSalary();

            if (existingSalary == null) {
                existingSalary = new Salary();
            }

            existingSalary.setBasicSalary(updatedEmployee.getSalary().getBasicSalary());
            existingSalary.setHra(updatedEmployee.getSalary().getHra());
            existingSalary.setAllowances(updatedEmployee.getSalary().getAllowances());
            existingSalary.setEmployee(existingEmployee);

            existingEmployee.setSalary(existingSalary);
        }


        if (updatedEmployee.getProjects() != null) {

            List<Project> newProjects = new ArrayList<>();

            for (Project p : updatedEmployee.getProjects()) {
                Project project = projectRepository.findById(p.getProjectId())
                        .orElseThrow(() -> new RuntimeException("Project not found"));
                newProjects.add(project);
            }

            existingEmployee.setProjects(newProjects);
        }
        return employeeRepository.save(existingEmployee);
    }


    @Override
    public Boolean deleteEmployeeById(Long empId) {
        employeeRepository.findById(empId)
                .orElseThrow(()-> new IllegalArgumentException("Employee Not Found By this ID"));
        employeeRepository.deleteById(empId);
        return true;
    }



    @Override
    public Employee updatePartialEmployee(Long empId, Map<String, Object> updatedList) {
//        Employee existedEmployee = employeeRepository
//                .findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee Not Found"));
//        for(Map.Entry<String,Object> entry:updatedList.entrySet()){
//            String filed=entry.getKey();
//            Object value=entry.getValue();
//         //  String object = (String) entry.getValue();
//            switch (filed){
//                case "name": existedEmploye.setName((String)value);
//                case "email": existedEmploye.setEmail((String)value);
//                case "phoneNumber" : existedEmploye.setPhoneNumber((Long) value);
//                default:
//                    throw new IllegalArgumentException("Field is not Present");
//            }
//        }

        Employee existedEmployee = employeeRepository
                .findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee Not Found"));
        updatedList.forEach((field,value)->{
            switch (field) {
                case "name": existedEmployee.setName(value.toString()); break;
                case "email": existedEmployee.setEmail(value.toString()); break;
                case "phoneNumber": existedEmployee.setPhoneNumber(Long.valueOf(value.toString())); break;
                default: throw new IllegalArgumentException("Field is not Present");
            }
        });
        return employeeRepository.save(existedEmployee);
    }
}
