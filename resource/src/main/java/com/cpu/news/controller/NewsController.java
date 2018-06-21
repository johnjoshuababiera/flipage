package com.cpu.news.controller;


import com.cpu.department.DepartmentService;
import com.cpu.news.News;
import com.cpu.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

@Controller
@RequestMapping("/page/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @Autowired
    private DepartmentService departmentService;

    @Value("${flipage.file.url}")
    private String FILE_URL;


    @RequestMapping("/")
    public String newsList(Model model) {
        model.addAttribute("newsList", service.findAll());
        return "news/news_list";
    }


    @RequestMapping("/create")
    public String create(Model model) {
        News news = new News();
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("news",news);
        return "news/news_form";
    }

    @RequestMapping("/view")
    public String view(@RequestParam long id, Model model){
        News news = service.findOne(id);
        model.addAttribute("news",news);
        return "news/news_view";
    }


    @RequestMapping("/edit")
    public String edit(@RequestParam long id, Model model){
        News news = service.findOne(id);
        model.addAttribute("news",news);
        model.addAttribute("departments", departmentService.findAll());
        return "news/news_form";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam long id, RedirectAttributes redir){
        service.delete(id);
        return "redirect:/page/news/";
    }

    @PostMapping("/save")
    public String saveNews(@ModelAttribute News news, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload");
            return "redirect:/page/news/";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_URL + file.getOriginalFilename());
            Files.write(path, bytes);
            news.setFilePath(file.getOriginalFilename());
            news.setDepartment(departmentService.findOne(news.getDepartmentId()));
            service.save(news);
            redirectAttributes.addFlashAttribute("info",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            return "redirect:/page/news/";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/page/news/";
    }
}
