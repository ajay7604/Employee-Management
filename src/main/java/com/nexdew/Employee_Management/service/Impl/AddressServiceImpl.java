package com.nexdew.Employee_Management.service.Impl;

import com.nexdew.Employee_Management.entity.Address;
import com.nexdew.Employee_Management.repository.AddressRepository;
import com.nexdew.Employee_Management.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

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

        existingAdd.setAddressId(updatedAddress.getAddressId());
        existingAdd.setCity(updatedAddress.getCity());
        existingAdd.setState(updatedAddress.getState());
        existingAdd.setStreet(updatedAddress.getStreet());

        return addressRepository.save(existingAdd);
    }

}
