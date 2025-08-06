package com.abbascoban.ui.rest;

import com.abbascoban.business.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IEmployeeController {
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id);
    public ResponseEntity<EmployeeDto> updatemEmployee(Long id,EmployeeDto employeeDto);
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);
}
