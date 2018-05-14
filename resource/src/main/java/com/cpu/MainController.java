package com.cpu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main() {
        //signin
        return "redirect:/page/news/";
    }
}
