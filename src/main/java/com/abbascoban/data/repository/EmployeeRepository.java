package com.abbascoban.data.repository;

import com.abbascoban.data.entity.EmployeeEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntitiy,Long> {
}
