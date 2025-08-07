package com.abbascoban.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Entity
@Table(name = "employees")
public class EmployeeEntitiy extends BaseEntitiy {

    @NotEmpty
    @Size(max = 50,min = 1)
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 50,min = 1)
    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;


}
