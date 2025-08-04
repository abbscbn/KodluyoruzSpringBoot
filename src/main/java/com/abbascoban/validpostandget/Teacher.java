package com.abbascoban.validpostandget;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class Teacher {

    private Long teacherId;
    @Size(max = 255,min = 1)
    @NotEmpty(message = "ad ve soyad kısmı boş olamaz")
    private String teacherNameSurname;

    @Email(message = "Email formatında olmak zorunda")
    @NotEmpty(message = "email alanı boş olamaz")
    private String teacherEmail;

    @Size(min = 10, max = 20, message = "şifre 10 ve 20 karakter arası olmak zorunda")
    @NotEmpty(message = "şifre alanı boş olamaz")
    private String teacherPassword;
}
