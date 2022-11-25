package com.Employeedetails.springbootproject.service;

import com.Employeedetails.springbootproject.entities.Employee;
import com.Employeedetails.springbootproject.payloads.EmployeeDto;
import com.Employeedetails.springbootproject.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = this.dtoToEmployee(employeeDto);
        Employee savedEmployee = this.employeeRepo.save(employee);
        return this.employeeToDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee, Long employeeId) {
        Employee employeeObj = this.employeeRepo.findById(employeeId).get();
        employeeObj.setEmpid(employee.getEmpid());
        employeeObj.setEmpname(employee.getEmpname());
        employeeObj.setDateofjoining(employee.getDateofjoining());
        employeeObj.setYearofexperience(employee.getYearofexperience());
        Employee updatedEmployee = this.employeeRepo.save(employeeObj);
        EmployeeDto employeeDto1 = this.employeeToDto(updatedEmployee);
        return employeeDto1;
    }

    @Override
    public EmployeeDto getById(Long employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId).get();

        return this.employeeToDto(employee);
    }



    @Override
    public List<EmployeeDto> getAllEmployees( String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }else if (sortDir.equalsIgnoreCase("desc"))
            sort=Sort.by(sortBy).descending();
        List<Employee> employees = this.employeeRepo.findAll(Sort.by(Sort.Direction.ASC,sortBy));
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> this.employeeToDto(employee)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId).get();
        this.employeeRepo.delete(employee);
    }

   public Employee dtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        return employee;
   }
//        Employee employee = new Employee();
//        employee.setEmp_id(employeeDto.getEmp_id());
//        employee.setEmp_name(employeeDto.getEmp_name());
//        employee.setDate_of_joining(employeeDto.getDate_of_joining());
//        employee.setYear_of_experience(employeeDto.getYear_of_experience());
//        employee.setDepartment(employeeDto.getDepartment());
//        return employee;
//    }
//
  public EmployeeDto employeeToDto(Employee employee) {
      EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);
      return employeeDto;
  }
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setEmp_id(employee.getEmp_id());
//        employeeDto.setEmp_name(employee.getEmp_name());
//        employeeDto.setDate_of_joining(employee.getDate_of_joining());
//        employeeDto.setYear_of_experience(employee.getYear_of_experience());
//        employeeDto.setDepartment(employee.getDepartment());
//        return employeeDto;
//    }

}
