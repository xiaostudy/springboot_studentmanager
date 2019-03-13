package com.xiaostudy.springboot_studentmanager.web.controller;

import com.xiaostudy.springboot_studentmanager.domain.Login;
import com.xiaostudy.springboot_studentmanager.service.LoginService;
import com.xiaostudy.util.MakeMD5;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

    /*@PostMapping("/login")
    public String login() {
        System.out.println("login");
        return "index";
    }*/

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        if(username == null || username.length() <= 0 || password == null || password.length() <= 0) {
            model.addAttribute("error", "<span style='color:red'>用户或密码为空！</span>");
            return "userlogin";
        }

        String username1 = (String) session.getAttribute("username");
        String password1 = (String) session.getAttribute("password");
        if(username1 != null && password1 != null) {
            return "index";
        }

        List<Login> list = loginService.getLoginByName(username.trim());
        System.out.println("list:" + list);
        System.out.println("MakeMD5.getMD5(\"2019001\") ====" + MakeMD5.getMD5("2019001"));
        if(list.size() <= 0 || list.size() > 1) {
            model.addAttribute("error", "<span style='color:red'>没有此用户或用户不唯一！</span>");
            return "userlogin";
        }
        Login login = list.get(0);
//        if(login.getName().equals(username.trim()) && login.getPassword().equals(MakeMD5.getMD5(password.trim()))) {
        if(login.getName().equals(username.trim()) && login.getPassword().equals(password.trim())) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return "index";
        }
        model.addAttribute("error", "<span style='color:red'>用户或密码错误！</span>");
        return "userlogin";
    }

    @GetMapping("/login")
    public String login() {
        return "userlogin";
    }




}
