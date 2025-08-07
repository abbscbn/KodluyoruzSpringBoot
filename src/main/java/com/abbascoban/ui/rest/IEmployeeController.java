package com.abbascoban.ui.rest;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.rootentity.RootEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public interface IEmployeeController {
    public RootEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto, WebRequest request);
    public RootEntity<EmployeeDto> getEmployeeById(Long id,WebRequest request);
    public RootEntity<EmployeeDto> updatemEmployee(Long id,EmployeeDto employeeDto,WebRequest request);
    public RootEntity<Map<String,Boolean>> deleteEmployee(Long id,WebRequest request);
}
