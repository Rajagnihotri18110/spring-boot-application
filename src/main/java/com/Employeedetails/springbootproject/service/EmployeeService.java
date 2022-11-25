package com.Employeedetails.springbootproject.service;

import com.Employeedetails.springbootproject.payloads.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employee);

    EmployeeDto updateEmployee(EmployeeDto user, Long userId);

    EmployeeDto getById(Long employeeId);

    List<EmployeeDto> getAllEmployees(String sortBy, String sortDir);

    void deleteEmployee(Long employeeId);
}
