package com.abbascoban.business.services.impl;

import com.abbascoban.business.dto.EmployeeDto;
import com.abbascoban.data.entity.EmployeeEntitiy;
import com.abbascoban.data.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//burada extend ediyoruz mıkitoyu
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    //ANA KATMAN OLDUĞU İÇİN InjectMoks ve diğerini AutoWired ettiği için Mock ile
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    EmployeeDto employeeDto = EmployeeDto.builder()
            .firstName("Abbas")
            .lastName("Çoban")
            .email("abbscbn@gmail.com")
            .build();

    EmployeeEntitiy employeeEntity = EmployeeEntitiy.builder()
            .firstName("Abbas")
            .lastName("Çoban")
            .email("abbscbn@gmail.com")
            .build();

    EmployeeDto forUpdateEmployeeDto = EmployeeDto.builder()
            .firstName("Burak")
            .lastName("Gülnaz")
            .email("brk@hotmail.com")
            .build();
    EmployeeEntitiy updatedEmployee= EmployeeEntitiy.builder()
            .firstName("Burak")
            .lastName("Gülnaz")
            .email("brk@hotmail.com")
            .build();

    @Test
    void createEmployee() {


        // modelmapper sınıfının map methodunu girilen parametreler gelirse tanımladığımz nesneyi direk geri dön
        Mockito.when(modelMapper.map(employeeDto, EmployeeEntitiy.class)).thenReturn(employeeEntity);
        // yukarıdaki açıklamanın aynısı
        Mockito.when(modelMapper.map(employeeEntity, EmployeeDto.class)).thenReturn(employeeDto);
        //burada test edeceğimiz create methodu çalıştırılırken repository katmanına gitmeden onun taklidini yapıyoruz
        Mockito.when(employeeRepository.save(ArgumentMatchers.any(EmployeeEntitiy.class))).thenReturn(employeeEntity);

        EmployeeDto result = employeeService.createEmployee(employeeDto);
        //burada da idda ettiğimiz durum doğruysa test başarılı
        assertEquals(result.getFirstName(), employeeDto.getFirstName());

    }
        @Test
        void getEmployeeById () {
            employeeEntity.setId(1L);


         Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employeeEntity));

         Mockito.when(modelMapper.map(employeeEntity, EmployeeDto.class)).thenReturn(employeeDto);

         ResponseEntity<EmployeeDto> result = employeeService.getEmployeeById(employeeEntity.getId());

         assertEquals(result.getBody().getFirstName(),employeeEntity.getFirstName());

        }

        @Test
        void updatemEmployee () {

        Long requestId=1L;

        Mockito.when(employeeRepository.findById(requestId)).thenReturn(Optional.ofNullable(employeeEntity));

        Mockito.when(employeeRepository.save(ArgumentMatchers.any(EmployeeEntitiy.class))).thenReturn(updatedEmployee);

        Mockito.when(modelMapper.map(updatedEmployee, EmployeeDto.class)).thenReturn(forUpdateEmployeeDto);

        ResponseEntity<EmployeeDto> result = employeeService.updatemEmployee(requestId, forUpdateEmployeeDto);

        assertEquals(forUpdateEmployeeDto.getFirstName(),result.getBody().getFirstName());

        }

        @Test
        void deleteEmployee () {

        Long requestId=1L;


        Mockito.when(employeeRepository.findById(requestId)).thenReturn(Optional.ofNullable(employeeEntity));

        ResponseEntity<Map<String, Boolean>> mapResponseEntity = employeeService.deleteEmployee(requestId);

        assertEquals(mapResponseEntity.getBody().get("deleted"),true);

        }

        @Test
        void entityToDto () {

        }

        @Test
        void dtoToEntity () {

        }

}