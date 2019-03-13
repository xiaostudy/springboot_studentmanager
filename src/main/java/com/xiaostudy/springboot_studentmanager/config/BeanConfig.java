package com.xiaostudy.springboot_studentmanager.config;

import com.xiaostudy.springboot_studentmanager.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name="login")
    public Login getLogin() {
        Login login = new Login();
        return login;
    }

    @Bean(name="clazz")
    public Clazz getClazz() {
        Clazz clazz = new Clazz();
        return clazz;
    }

    @Bean(name="grade")
    public Grade getGrade() {
        Grade grade = new Grade();
        return grade;
    }

    @Bean(name="results")
    public Results getResults() {
        Results results = new Results();
        return results;
    }

    @Bean(name="student")
    public Student getStudent() {
        Student student = new Student();
        return student;
    }

    @Bean(name="subject")
    public Subject getSubject() {
        Subject subject = new Subject();
        return subject;
    }

    @Bean(name="teacher")
    public Teacher getTeacher() {
        Teacher teacher = new Teacher();
        return teacher;
    }

    @Bean(name="test")
    public Test getTest() {
        Test test = new Test();
        return test;
    }
}