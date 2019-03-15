package com.xiaostudy.springboot_studentmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/testlist")
    public String gradelist() {
        return "testlist";
    }
}
