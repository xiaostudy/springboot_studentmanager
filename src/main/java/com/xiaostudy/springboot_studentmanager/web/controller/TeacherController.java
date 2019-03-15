package com.xiaostudy.springboot_studentmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherController {

    @RequestMapping("/teacherlist")
    public String gradelist() {
        return "teacherlist";
    }
}
