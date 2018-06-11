package com.cpu;

import com.cpu.user.UserService;
import com.cpu.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String main() {
//        try {
//            if(userService.noAdmin()){
//                userService.initializeAdmin();
//        }
//
//        if(SignInUtils.getInstance().getCurrentUser()==null){
//                return "redirect:/login";
//        }
        return "redirect:/page/news/";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
