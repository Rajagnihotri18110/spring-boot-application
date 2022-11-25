package com.Employeedetails.springbootproject.controller;

import com.Employeedetails.springbootproject.payloads.ApiResponse;
import com.Employeedetails.springbootproject.payloads.EmployeeDto;
import com.Employeedetails.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createUser(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto createEmployeeDto = this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId)
    {
        EmployeeDto updateEmployee = this.employeeService.updateEmployee(employeeDto, employeeId);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("employeeId") Long employeeId)
    {
        this.employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new ApiResponse("User successfully deleted", true), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees
            (
             @RequestParam(value = "sortBy", defaultValue = "empid", required = false) String sortBy,
             @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir)
    {

        return ResponseEntity.ok(this.employeeService.getAllEmployees(sortBy,sortDir));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId)
    {
        return ResponseEntity.ok(this.employeeService.getById(employeeId));
    }
}
