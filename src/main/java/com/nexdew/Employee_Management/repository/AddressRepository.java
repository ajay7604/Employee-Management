package com.nexdew.Employee_Management.repository;

import com.nexdew.Employee_Management.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
