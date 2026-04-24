package com.re.ss8.bai2.controller;

import com.re.ss8.bai2.dto.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    // Đây là URL bạn sẽ gõ: localhost:8080/hr/add-employee
    @GetMapping("/hr/add-employee")
    public String showForm(Model model) {
        model.addAttribute("employee", new EmployeeDto());


        return "employee-form";
    }

    @PostMapping("/hr/add-employee")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDto employee,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee-form"; // Nếu lỗi, quay lại form
        }
        return "redirect:/hr/success";
    }

    @GetMapping("/hr/success")
    public String showSuccessPage() {
        return "success"; // Tên file success.jsp trong thư mục views
    }
}