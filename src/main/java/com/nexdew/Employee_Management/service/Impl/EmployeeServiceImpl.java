package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.repository.EmployeeRepository;
import com.nexdew.Employee_Management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;


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
            switch (field){
                case "name": existedEmployee.setName((String)value);
                case "email": existedEmployee.setEmail((String)value);
                case "phoneNumber" : existedEmployee.setPhoneNumber((Long) value);
                default:
                    throw new IllegalArgumentException("Field is not Present");
            }
        });
        return employeeRepository.save(existedEmployee);


    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Boolean deleteEmployeeById(Long empId) {
        employeeRepository.findById(empId)
                .orElseThrow(()-> new IllegalArgumentException("Employee Not Found By this ID"));
        employeeRepository.deleteById(empId);
        return true;
    }

    @Override
    public Employee updateEmployee(Long empId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository
                .findById(empId).orElseThrow(() -> new RuntimeException("Employee not found:" +empId));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployee.getEmail());

        return employeeRepository.save(existingEmployee);

    }


}
