package com.xiaostudy.springboot_studentmanager;

import com.xiaostudy.springboot_studentmanager.dao.ClazzDao;
import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import com.xiaostudy.springboot_studentmanager.domain.Login;
import com.xiaostudy.springboot_studentmanager.domain.Teacher;
import com.xiaostudy.springboot_studentmanager.service.LoginService;
import com.xiaostudy.springboot_studentmanager.service.TeacherService;
import com.xiaostudy.util.MakeMD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStudentmanagerApplicationTests {

    @Autowired
    public ClazzDao clazzDao;

    @Autowired
    public TeacherService teacherService;

    @Autowired
    public LoginService loginService;

    @Test
    public void contextLoads() {
        Clazz clazz = clazzDao.getOne(2);
        System.out.println(clazz.toString());
        List<Clazz> all = clazzDao.findAll();
        System.out.println(all);
        boolean b = clazzDao.existsById(1);
        System.out.println(b);
    }

    @Test
    public void test1() {
        String md5 = MakeMD5.getMD5("123456");
        System.out.println(md5);
    }

    @Test
    public void test2() {
        Teacher xiaostudy = teacherService.getTeacherByTeacherName("xiaostudy");
        System.out.println(xiaostudy);
    }

    @Test
    public void test3() {
        List<Login> xiaostudy = loginService.getLoginByName("xiaostudy");
        System.out.println(xiaostudy);
    }

}
