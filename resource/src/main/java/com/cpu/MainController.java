package com.cpu;

import com.cpu.department.Department;
import com.cpu.department.DepartmentService;
import com.cpu.user.User;
import com.cpu.user.UserService;
import com.cpu.utils.DatabaseDto;
import com.cpu.utils.DateTimeUtil;
import com.cpu.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${db.username}")
    private String DB_USERNAME;
    @Value("${db.password}")
    private String DB_PASSWORD;


    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/")
    public String main(Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        try {
            if (userService.noAdmin()) {
                userService.initializeAdmin();
            }
            if (SignInUtils.getInstance().getCurrentUser() == null) {
                redir.addFlashAttribute("error", model.asMap().get("error"));
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        redir.addFlashAttribute("success", model.asMap().get("success"));
        return "redirect:/page/news/";
    }


    @RequestMapping("/backup")
    public String backup(RedirectAttributes redir) {
        try {
            if (SignInUtils.getInstance().getCurrentUser() == null) {
                redir.addFlashAttribute("error", "Invalid access!");
                return "redirect:/";
            }

            String fileName = "backup-".concat(DateTimeUtil.dateLongToString(System.currentTimeMillis(), "MM-dd-yyyy").concat(".sql"));
            String executeCmd = "mysqldump -u" + DB_USERNAME + " -p" + DB_PASSWORD + " --add-drop-database -B flipage -r " +fileName;

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);


            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                redir.addFlashAttribute("success", "Databased backed up to: "+ fileName);
            } else {
                redir.addFlashAttribute("error", "Backup failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/restoreBackup")
    public String restore(@ModelAttribute DatabaseDto databaseDto, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            redir.addFlashAttribute("error", "Invalid access!");
            return "redirect:/";
        }
        String[] executeCmd = new String[]{"mysql", "--user=" + DB_USERNAME, "--password=" + DB_PASSWORD, "-e", "source "+databaseDto.getFileName()};
        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                redir.addFlashAttribute("success", databaseDto.getFileName()+" database restored");
            } else {
                redir.addFlashAttribute("error", "Restore failed.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage(Model model, RedirectAttributes redir) {
        if (userService.noAdmin()) {
            try {
                userService.initializeAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (SignInUtils.getInstance().getCurrentUser() != null) {
            return "redirect:/page/news/";
        }
        model.addAttribute("user", new User());
        return "/login.html";
    }

    @RequestMapping("/signout")
    public String logout() {
        SignInUtils.getInstance().SignOut();
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute User user, RedirectAttributes redir) {
        User savedUser = userService.getUser(user.getIdNumber(), user.getPassword());
        if (savedUser == null) {
            redir.addFlashAttribute("error","Invalid username/password!");
            return "redirect:/login";
        }
        if (!savedUser.isAdmin()) {
            redir.addFlashAttribute("error","Invalid restrictions!");
            return "redirect:/login";
        }
        SignInUtils.getInstance().SignIn(savedUser);
        return "redirect:/";

    }


    @RequestMapping("/register")
    public String register(Model model, RedirectAttributes redir) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("user", new User());
        return "/register.html";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redir) {
        try {
            Department department = departmentService.findOne(user.getDeptId());
            user.setDepartment(department);
            user.setAdmin(true);
            userService.save(user);
        } catch (Exception e) {
            redir.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/";

    }

}
