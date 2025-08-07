package com.abbascoban.ui.rest.impl;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.business.services.IEmployeeServices;
import com.abbascoban.rootentity.RootEntity;
import com.abbascoban.ui.rest.IEmployeeController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeControllerImpl extends RootEntity implements IEmployeeController {

    @Autowired
    private IEmployeeServices employeeServices;

    @PostMapping("/employees")
    @Override
    public RootEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, WebRequest request) {
       return RootEntity.ok(employeeServices.createEmployee(employeeDto),request);
    }

    @GetMapping("/employees/{id}")
    @Override
    public RootEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id,WebRequest request) {
        employeeServices.getEmployeeById(id);
        return RootEntity.ok(employeeServices.getEmployeeById(id).getBody(),request);
    }

    @PutMapping("/employees/{id}")
    @Override
    public RootEntity<EmployeeDto> updatemEmployee(@PathVariable(name = "id") Long id,@RequestBody EmployeeDto employeeDto, WebRequest request) {
        return RootEntity.ok(employeeServices.updatemEmployee(id,employeeDto).getBody(), request);
    }

    @DeleteMapping("/employees/{id}")
    @Override
    public RootEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id, WebRequest request) {
        return RootEntity.ok(employeeServices.deleteEmployee(id).getBody(), request);
    }
}
