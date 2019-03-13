package com.xiaostudy.springboot_studentmanager.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能，效果，如果没有登录，没有权限就会来到登录页面
        http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/userlogin");
        //1、/login来到登录页
        //2、重定向到/login?error表示失败
        //3、更多详细规则

        //开始自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功后来到首页

        //开始记住我功能
        http.rememberMe();
        //登录成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
       /* auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())//springboot2.x以后
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
                .and()
                .withUser("huangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP3");*/
        auth.inMemoryAuthentication()//用户名、密码、权限等级
                .withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("huangwu").password("123456").roles("VIP1", "VIP3");

//        auth.userDetailsService(customUserService()).passwordEncoder(new MyPasswordEncoder());//数据库认证
    }
}