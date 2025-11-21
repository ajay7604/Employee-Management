package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Address;
import com.nexdew.Employee_Management.entity.Department;
import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.entity.Salary;
import com.nexdew.Employee_Management.repository.*;
import com.nexdew.Employee_Management.service.DepartmentService;
import com.nexdew.Employee_Management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

            Department dept = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));

            existingEmployee.setDepartment(dept);
        }

        // ---------- Update Address ----------
        if (updatedEmployee.getAddress() != null) {

            Address existingAddress = existingEmployee.getAddress();

            if (existingAddress == null) {
                existingAddress = new Address(); // only when first time
            }

            existingAddress.setStreet(updatedEmployee.getAddress().getStreet());
            existingAddress.setCity(updatedEmployee.getAddress().getCity());
            existingAddress.setEmployee(existingEmployee);

            existingEmployee.setAddress(existingAddress);
        }

        // ---------- Update Salary ----------
        if (updatedEmployee.getSalary() != null) {

            Salary existingSalary = existingEmployee.getSalary();

            if (existingSalary == null) {
                existingSalary = new Salary(); // only when first time
            }

            existingSalary.setBasicSalary(updatedEmployee.getSalary().getBasicSalary());
            existingSalary.setHra(updatedEmployee.getSalary().getHra());
            existingSalary.setAllowances(updatedEmployee.getSalary().getAllowances());
            existingSalary.setEmployee(existingEmployee);

            existingEmployee.setSalary(existingSalary);
        }

        // ---------- Update Projects ----------
        if (updatedEmployee.getProjects() != null) {
            existingEmployee.setProjects(updatedEmployee.getProjects());
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
        Employee existedEmployee = employeeRepository
                .findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee Not Found"));
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
        updatedList.forEach((field,value)->{
            switch (field) {
                case "name": existedEmployee.setName((String)value); break;
                case "email": existedEmployee.setEmail((String)value); break;
                case "phoneNumber": existedEmployee.setPhoneNumber((Long) value); break;
                default: throw new IllegalArgumentException("Field is not Present");
            }
        });
        return employeeRepository.save(existedEmployee);

    }
}
