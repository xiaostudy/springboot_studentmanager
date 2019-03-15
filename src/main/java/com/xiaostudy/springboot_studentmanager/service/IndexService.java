package com.xiaostudy.springboot_studentmanager.service;

import com.xiaostudy.springboot_studentmanager.domain.Subject;
import com.xiaostudy.springboot_studentmanager.domain.Test;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 验证登录service接口
 * 
 * @author liwei
 *
 */
public interface IndexService {

	public Boolean isLogin(HttpSession session);

}