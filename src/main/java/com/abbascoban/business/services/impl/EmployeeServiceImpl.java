package com.abbascoban.business.services.impl;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.business.services.IEmployeeServices;
import com.abbascoban.data.entity.EmployeeEntitiy;
import com.abbascoban.data.repository.EmployeeRepository;
import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.ErrorMessageType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntitiy employeeEntitiy= DtoToEntity(employeeDto); //Dto dan Entiityye çevirme
        EmployeeEntitiy savedEmployee = employeeRepository.save(employeeEntitiy);
        return EntityToDto(savedEmployee);
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(id).
                orElseThrow(() -> new BaseExcepiton(new ErrorMessage(ErrorMessageType.NOT_FOUND,"ID: "+id.toString())));
        EmployeeDto employeeDto = EntityToDto(employeeEntitiy);
        return ResponseEntity.ok(employeeDto);
    }

    @Override
    public ResponseEntity<EmployeeDto> updatemEmployee(Long id,EmployeeDto employeeDto) {

        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(id).
                orElseThrow(() -> new BaseExcepiton(new ErrorMessage(ErrorMessageType.NOT_FOUND,id.toString())));

         BeanUtils.copyProperties(employeeDto,employeeEntitiy);
        return ResponseEntity.ok(EntityToDto(employeeRepository.save(employeeEntitiy)));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(id).orElseThrow(() -> new BaseExcepiton(new ErrorMessage(ErrorMessageType.NOT_FOUND,"ID: "+id.toString())));
        employeeRepository.delete(employeeEntitiy);
        Map<String,Boolean> response= new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //Entity'den Dto ya çevirmek için
    @Override
    public EmployeeDto EntityToDto(EmployeeEntitiy employeeEntitiy) {
        return modelMapper.map(employeeEntitiy, EmployeeDto.class);
    }
    //Dto dan Entitiy e çevirmek için
    @Override
    public EmployeeEntitiy DtoToEntity(EmployeeDto employeeDto) {

        return modelMapper.map(employeeDto, EmployeeEntitiy.class);
    }
}
