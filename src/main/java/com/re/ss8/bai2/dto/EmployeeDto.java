package com.re.ss8.bai2.dto;

import jakarta.validation.constraints.*;

public class EmployeeDto {

    @NotBlank(message = "Tên nhân viên không được để trống")
    private String name;

    @Min(value = 18, message = "Nhân viên phải từ 18 tuổi trở lên")
    @Max(value = 60, message = "Tuổi nhân viên không được quá 60")
    private Integer age;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng (ví dụ: abc@gmail.com)")
    private String email;

    // Getters và Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}