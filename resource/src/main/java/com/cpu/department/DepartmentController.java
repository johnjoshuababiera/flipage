package com.cpu.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/page/department")
public class DepartmentController
{

    @Autowired
    private DepartmentService service;

    @RequestMapping("/")
    public String main(Model model) {
        Department department = new Department();
        model.addAttribute("department",department);
        return "/index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Department department = new Department();
        model.addAttribute("department",department);
        return "/department/department_form";
    }


    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes){

        return "redirect:/page/department/";
    }
}