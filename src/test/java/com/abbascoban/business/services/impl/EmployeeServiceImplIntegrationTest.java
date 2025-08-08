package com.abbascoban.business.services.impl;


import com.abbascoban.business.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
//Gerçek zamanlı test tipidir burada soyutlama yoktur direk katmanları ziyaret edip çalışmasını sağlar
@SpringBootTest
public class EmployeeServiceImplIntegrationTest {

    @Autowired
    EmployeeServiceImpl employeeService;

    EmployeeDto employeeDto= EmployeeDto.builder()
            .firstName("Abbas")
            .lastName("Çoban")
            .email("abbascbn@gmail.com")
            .build();


    @Test
    public void createEmployeeTest() {

        EmployeeDto result = employeeService.createEmployee(employeeDto);

        assertEquals(employeeDto.getFirstName(),result.getFirstName());

    }

}
