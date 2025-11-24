package com.nexdew.Employee_Management.service;

import com.nexdew.Employee_Management.entity.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {
    Address createAddress(Address address);

    Address getById(Long addressId);

    List<Address> getAllAddress();

    Boolean deleteAddressById(Long addressId);

    Address updateAddress(Long addressId, Address updatedAddress);

    Address getPartialUpdates(Long addressId, Map<String, Object> updatedListAddress);
}
