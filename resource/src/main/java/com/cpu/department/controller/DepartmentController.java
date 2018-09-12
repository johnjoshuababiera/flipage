package com.cpu.department.controller;

import com.cpu.department.Department;
import com.cpu.department.DepartmentService;
import com.cpu.news.News;
import com.cpu.news.NewsService;
import com.cpu.utils.DatabaseDto;
import com.cpu.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/page/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/")
    public String newsList(Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        model.addAttribute("departments", service.findAll());
        return "/department/department_list.html";
    }


    @RequestMapping("/create")
    public String create(Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        Department department = new Department();
        model.addAttribute("department", department);
        return "/department/department_form.html";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department, RedirectAttributes redir, @RequestParam MultipartFile file) {
        try {

            byte[] imageBytes = Base64.getEncoder().encode(file.getBytes());
            department.setImage(new String(imageBytes));
            service.save(department);
            redir.addFlashAttribute("success", "Department saved!");
            return "redirect:/page/department/";
        } catch (IOException e) {
            e.printStackTrace();
            redir.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/page/department/create";
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam long id, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        if (service.checkUsed(id)) {
            redir.addFlashAttribute("error", "Department is used! Cannot be deleted");
        } else {
            service.delete(id);
        }
        redir.addFlashAttribute("success", "Department deleted");
        return "redirect:/page/department/";
    }
}