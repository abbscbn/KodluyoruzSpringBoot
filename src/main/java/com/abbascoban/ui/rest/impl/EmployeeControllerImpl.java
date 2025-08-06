package com.abbascoban.ui.rest.impl;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.business.services.IEmployeeServices;
import com.abbascoban.ui.rest.IEmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeControllerImpl implements IEmployeeController {

    @Autowired
    private IEmployeeServices employeeServices;

    @PostMapping("/employees")
    @Override
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
       return employeeServices.createEmployee(employeeDto);
    }

    @GetMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        return employeeServices.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> updatemEmployee(@PathVariable(name = "id") Long id,@RequestBody EmployeeDto employeeDto) {
        return employeeServices.updatemEmployee(id,employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        return employeeServices.deleteEmployee(id);
    }
}
