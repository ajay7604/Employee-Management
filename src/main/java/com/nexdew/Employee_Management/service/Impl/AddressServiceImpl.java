package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.dto.AddressRequestDTO;
import com.nexdew.Employee_Management.entity.Address;
import com.nexdew.Employee_Management.entity.Employee;
import com.nexdew.Employee_Management.repository.AddressRepository;
import com.nexdew.Employee_Management.repository.EmployeeRepository;
import com.nexdew.Employee_Management.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {


    private final  AddressRepository addressRepository;

    private final EmployeeRepository employeeRepository;

//    @Override
//    public Address createAddress(Address address) {
//
//        if (address.getEmployee() == null || address.getEmployee().getEmployeeId() == null) {
//            throw new RuntimeException("Employee ID must be provided for address");
//        }
//
//        Long employeeId = address.getEmployee().getEmployeeId();
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
//
//        address.setEmployee(employee);
//        employee.setAddress(address);
//
//        return addressRepository.save(address);
//
//    }

    @Override
    public Address getById(Long addressId) {
       return addressRepository.findById(addressId)
                .orElseThrow(()-> new RuntimeException("Address not found by this id"));

    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Boolean deleteAddressById(Long addressId) {
        addressRepository.findById(addressId)
                .orElseThrow(()-> new RuntimeException("Address not found by this id"));
        addressRepository.deleteById(addressId);
        return true;
    }

    @Override
    public Address updateAddress(Long addressId, Address updatedAddress) {
        Address existingAdd = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found by this id"));

        existingAdd.setCity(updatedAddress.getCity());
        existingAdd.setState(updatedAddress.getState());
        existingAdd.setStreet(updatedAddress.getStreet());

        return addressRepository.save(existingAdd);
    }

    @Override
    public Address getPartialUpdates(Long addressId, Map<String, Object> updatedListAddress) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("address not found:" + addressId));
        updatedListAddress.forEach((field,value)->{
            switch (field){
                case "street":existingAddress.setStreet(value.toString());break;
                case "city": existingAddress.setCity(value.toString());break;
                case "state":existingAddress.setState(value.toString());break;
                default: throw new IllegalArgumentException("field not found ");
            }
        });
        return addressRepository.save(existingAddress);
    }

    @Override
    public Address createAddress(AddressRequestDTO addressRequestDTO) {
        Employee employee = employeeRepository.findById(addressRequestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found:"));

        Address address = new Address();
        address.setState(addressRequestDTO.getState());
        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());

        address.setEmployee(employee);
        employee.setAddress(address);

        return addressRepository.save(address);

    }

//    @Override
//    public Address createAddress(Long employeeId, Address address) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new IllegalArgumentException("Employee Not Found BY this id:" + employeeId));
//
//        address.setEmployee(employee);
//        employee.setAddress(address);
//
//        return addressRepository.save(address);
//    }

}
