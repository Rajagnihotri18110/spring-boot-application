package com.Employeedetails.springbootproject.payloads;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class EmployeeDto {
    private Long empid;
    private String empname;
    private Date dateofjoining;
    private Integer yearofexperience;
    private String department;

}
