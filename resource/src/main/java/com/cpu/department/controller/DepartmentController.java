package com.cpu.department.controller;

import com.cpu.department.Department;
import com.cpu.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/page/department")
public class DepartmentController
{

    @Autowired
    private DepartmentService service;

    @RequestMapping("/")
    public String newsList(Model model) {
        model.addAttribute("departments", service.findAll());
        return "department/department_list";
    }


    @RequestMapping("/create")
    public String create(Model model) {
        Department department = new Department();
        model.addAttribute("department",department);
        return "department/department_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department, RedirectAttributes redirectAttributes){
        service.save(department);
        return "redirect:/page/department/";

    }
}