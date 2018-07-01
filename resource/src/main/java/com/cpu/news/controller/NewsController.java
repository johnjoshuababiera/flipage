package com.cpu.news.controller;


import com.cpu.department.DepartmentService;
import com.cpu.news.News;
import com.cpu.news.NewsService;
import com.cpu.user.User;
import com.cpu.utils.SignInUtils;
import org.apache.commons.io.FilenameUtils;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String newsList(Model model, RedirectAttributes redir) {
        User user = SignInUtils.getInstance().getCurrentUser();
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("newsList",user.getIdNumber().equals("admin")? service.findAll():service.findByUserId(user.getId()));
        return "news/news_list";
    }


    @RequestMapping("/create")
    public String create(Model model, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        News news = new News();
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("news", news);
        return "news/news_form";
    }

    @RequestMapping("/view")
    public String view(@RequestParam long id, Model model, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        News news = service.findOne(id);
        model.addAttribute("news", news);
        return "news/news_view";
    }


    @RequestMapping("/edit")
    public String edit(@RequestParam long id, Model model, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        News news = service.findOne(id);
        model.addAttribute("news", news);
        model.addAttribute("departments", departmentService.findAll());
        return "news/news_form";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam long id, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            redir.addFlashAttribute("error", "Please login!");
            return "redirect:/";
        }
        service.delete(id);
        redir.addFlashAttribute("error", "News deleted!");
        return "redirect:/page/news/";
    }

    @PostMapping("/save")
    public String saveNews(@ModelAttribute News news, @RequestParam MultipartFile[] files, RedirectAttributes redir) {
        try {
            if(news.getId()!=null){
                News savedNews = service.findOne(news.getId());
                news.setImage(savedNews.getImage());
                news.setFileName(savedNews.getFileName());
            }
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                if (fileExtension.equals("pdf")) {
                    if (file == null) {
                        redir.addFlashAttribute("error", "Please select a file to upload");
                        return "redirect:/page/news/";
                    }
//                    Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(FILE_URL + file.getOriginalFilename());
                    Files.write(path, bytes);
                    news.setFileName(file.getOriginalFilename());
                    news.setFilePath(FILE_URL + file.getOriginalFilename());
                } else {
                    byte[] imageBytes = Base64.getEncoder().encode(file.getBytes());
                    news.setImage(new String(imageBytes));
                }
            }
            news.setDepartment(departmentService.findOne(news.getDeptId()));
            news.setUserId(SignInUtils.getInstance().getCurrentUser().getId());
            service.save(news);

        } catch (IOException e) {
            e.printStackTrace();
        }
        redir.addFlashAttribute("success", "News Saved!");
        return "redirect:/page/news/";
    }
}
