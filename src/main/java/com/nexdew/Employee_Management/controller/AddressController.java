package com.nexdew.Employee_Management.controller;

import com.nexdew.Employee_Management.entity.Address;
import com.nexdew.Employee_Management.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping("/create-address")
    public ResponseEntity<Address> createAddress(@RequestBody Address address ){
      Address addresses =  addressService.createAddress(address);
        return new ResponseEntity<>(addresses, HttpStatus.CREATED);

    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getByAddressById(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.getById(addressId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Boolean> deleteAddressById(@PathVariable Long addressId){
        return ResponseEntity. ok(addressService.deleteAddressById(addressId));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable  Long addressId, @RequestBody Address updatedAddress) {
        return ResponseEntity.ok(addressService.updateAddress(addressId,updatedAddress));
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<Address> partialUpdate(@PathVariable Long addressId, @RequestBody Map<String, Object> updatedListAddress){
        return ResponseEntity.ok(addressService.getPartialUpdates(addressId,updatedListAddress));
    }

}
