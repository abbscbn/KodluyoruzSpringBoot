package com.abbascoban;

import com.abbascoban.data.entity.EmployeeEntitiy;
import com.abbascoban.data.repository.EmployeeRepository;
import com.abbascoban.test.ITestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//Proje ayakta iken yapılan test
@SpringBootTest
class KodluyoruzSpringBootApplicationTests implements ITestCrud {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	@Override
	public void testCreate() {

		EmployeeEntitiy employeeEntitiy= EmployeeEntitiy.builder()
				.firstName("Abbas")
				.lastName("Çoban")
				.email("abbas@hotmal.com")
				.build();

		employeeRepository.save(employeeEntitiy);

		assertNotNull(employeeRepository.findById(1L).get());

	}

	@Test
	@Override
	public void testGetById() {

		EmployeeEntitiy employeeEntitiy = employeeRepository.findById(1L).get();

		assertEquals("Abbas",employeeEntitiy.getFirstName());

	}

	@Test
	@Override
	public void testUpdate() {

		EmployeeEntitiy employeeEntitiy = employeeRepository.findById(1L).get();
		employeeEntitiy.setFirstName("Atlas");
		employeeRepository.save(employeeEntitiy);
		//Eşit değil ise sorun yoktur
		assertNotEquals("Abbas",employeeRepository.findById(1L).get().getFirstName());

	}

	@Test
	@Override
	public void testDelete() {

		employeeRepository.deleteById(1L);

		assertThat(employeeRepository.existsById(1L)).isFalse();

	}
}
