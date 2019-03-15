package com.xiaostudy.springboot_studentmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/loginlist")
    public String gradelist() {
        return "loginlist";
    }
}
