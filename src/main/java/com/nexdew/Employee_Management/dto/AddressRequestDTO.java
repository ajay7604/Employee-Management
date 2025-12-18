package com.nexdew.Employee_Management.dto;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private Long employeeId;
    private String street;
    private String city;
    private String state;
}
