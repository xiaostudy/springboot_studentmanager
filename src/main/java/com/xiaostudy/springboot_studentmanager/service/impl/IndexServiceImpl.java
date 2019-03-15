package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.ClazzDao;
import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.domain.Teacher;
import com.xiaostudy.springboot_studentmanager.service.ClazzService;
import com.xiaostudy.springboot_studentmanager.service.GradeService;
import com.xiaostudy.springboot_studentmanager.service.IndexService;
import com.xiaostudy.springboot_studentmanager.service.TeacherService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级service接口实现类
 * 
 * @author liwei
 *
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {


	@Override
	public Boolean isLogin(HttpSession session) {
		String username_session = (String) session.getAttribute("username");
		String password_session = (String) session.getAttribute("password");
		System.out.println("username_session:" + username_session);
		System.out.println("password_session:" + password_session);
		if(username_session != null && password_session != null) {
			return true;
		}

		return false;
	}
}
