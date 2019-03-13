package com.xiaostudy.springboot_studentmanager.web.controller;

import com.xiaostudy.util.MakeMD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MD5Controller {

    @ResponseBody
    @RequestMapping("/md5")
    public String getMd5(String password) {
        return MakeMD5.getMD5(password);
    }
}
