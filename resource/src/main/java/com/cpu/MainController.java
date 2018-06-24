package com.cpu;

import com.cpu.department.Department;
import com.cpu.department.DepartmentService;
import com.cpu.user.User;
import com.cpu.user.UserService;
import com.cpu.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/")
    public String main() {
        try {
            if (userService.noAdmin()) {
                userService.initializeAdmin();
            }
            if (SignInUtils.getInstance().getCurrentUser() == null) {
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/page/news/";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        if (userService.noAdmin()) {
            try {
                userService.initializeAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(SignInUtils.getInstance().getCurrentUser()!=null){
            return "redirect:/page/news/";
        }
        model.addAttribute("user", new User());
        return "/login";
    }

    @RequestMapping("/signout")
    public String logout() {
        SignInUtils.getInstance().SignOut();
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        User savedUser = userService.getUser(user.getIdNumber(), user.getPassword());
        if(savedUser==null){
            return "redirect:/login";
        }
        SignInUtils.getInstance().SignIn(savedUser);
        return "redirect:/";

    }


    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("user", new User());
        return "/register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redir) {
        try {
            Department department = departmentService.findOne(user.getDeptId());
            user.setDepartment(department);
            user.setAdmin(true);
            userService.save(user);
        } catch (Exception e) {
            redir.addFlashAttribute("error",e.getMessage());
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/";

    }

}
