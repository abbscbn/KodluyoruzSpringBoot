package com.abbascoban.business.services;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.data.entity.EmployeeEntitiy;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IEmployeeServices {
    //CRUD
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id);
    public ResponseEntity<EmployeeDto> updatemEmployee(Long id,EmployeeDto employeeDto);
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);


    //model mapper
    public EmployeeDto EntityToDto(EmployeeEntitiy employeeEntitiy);
    public EmployeeEntitiy DtoToEntity(EmployeeDto employeeDto);
}
