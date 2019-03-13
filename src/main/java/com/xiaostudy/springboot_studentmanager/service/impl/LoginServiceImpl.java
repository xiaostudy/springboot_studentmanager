package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.LoginDao;
import com.xiaostudy.springboot_studentmanager.domain.Login;
import com.xiaostudy.springboot_studentmanager.domain.Student;
import com.xiaostudy.springboot_studentmanager.domain.Teacher;
import com.xiaostudy.springboot_studentmanager.service.LoginService;
import com.xiaostudy.springboot_studentmanager.service.StudentService;
import com.xiaostudy.springboot_studentmanager.service.TeacherService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import com.xiaostudy.util.MakeMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录service接口实现类
 *
 * @author liwei
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Override
	public List<Login> getLoginAll() {
		return loginDao.findAll();
	}

	@Override
	public Login getLoginByLoginId(Integer loginId) {
		if(loginId == null) {
			return null;
		}
		return loginDao.getOne(loginId);
	}

	@Override
	public List<Login> getLoginByPosition(String position) {
		if(position == null) {
			return null;
		}

		List<Login> list = new ArrayList<>();
		for(Login login : getLoginAll()) {
			if(login != null && login.getPosition().equals(position.trim())) {
				list.add(login);
			}
		}

		return list;
	}

	@Override
	public List<Login> getLoginByName(String name) {
		if(name == null || name.trim().length() <= 0) {
			return null;
		}

		List<Login> list = new ArrayList<>();
		for(Login login : getLoginAll()) {
			if(login != null && login.getName().equals(name.trim())) {
				list.add(login);
			}
		}

		if(list.size() == 0) {
			Teacher teacher = teacherService.getTeacherByTeacherName(name.trim());
			Login login = SpringUtil.getBean(Login.class);
			if(teacher != null) {
				login.setName(teacher.getTeacherName());
				login.setPosition("教师");
				login.setPassword(MakeMD5.getMD5(teacher.getTeacherNumber()));
				login.setPasswordPrompt("密码默认为职工号！");
				boolean b = insert(login);
				if(b) {
					list.add(login);
				}
			}

			Student student = studentService.getStudentByStudentName(name.trim());
			if(student != null) {
				login.setName(student.getStudentName());
				login.setPosition("学生");
				login.setPassword(MakeMD5.getMD5(student.getStudentNumber()));
				login.setPasswordPrompt("密码默认为学号！");
				boolean b = insert(login);
				if(b) {
					list.add(login);
				}
			}
		}

		return list;
	}

	@Override
	public boolean deleteByLoginId(Integer loginId) {
		if(loginId == null) {
			return false;
		}
		Login login = loginDao.getOne(loginId);
		if(login == null) {
			return false;
		}
		loginDao.deleteById(loginId);
		if(loginDao.existsById(loginId) == false) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Login login) {
		if(login == null) {
			return false;
		}

		if(login.getPosition() != null && login.getName() != null && login.getPassword() != null && login.getPasswordPrompt() != null) {
			Login save = loginDao.save(login);
			if(save != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateLoginIdByLoginId(Integer oldLoginId, Integer newLoginId) {
		if(oldLoginId == null || newLoginId == null) {
			return false;
		}
		if(oldLoginId.equals(newLoginId)) {
			return false;
		}
		Login login = loginDao.getOne(oldLoginId);
		if(login == null) {
			return false;
		}
		login.setLoginId(newLoginId);
		Login save = loginDao.save(login);
		if(save != null && !save.getLoginId().equals(oldLoginId)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateNameByLoginId(Integer loginId, String newName) {
		if(loginId == null || newName == null || newName.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.getOne(loginId);
		if(login == null) {
			return false;
		}
		if(newName.equals(login.getName())) {
			return false;
		}
		String oldName = login.getName();
		login.setName(newName);
		Login save = loginDao.save(login);
		if(save != null && !save.getName().equals(oldName)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordByLoginId(Integer loginId, String newPassword) {
		if(loginId == null || newPassword == null || newPassword.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.getOne(loginId);
		if(login == null) {
			return false;
		}
		if(newPassword.equals(login.getPassword())) {
			return false;
		}
		String oldPassword = login.getPassword();
		login.setPassword(newPassword);
		Login save = loginDao.save(login);
		if(save != null && !save.getPassword().equals(oldPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordPromptByLoginId(Integer loginId, String newPasswordPrompt) {
		if(loginId == null || newPasswordPrompt == null || newPasswordPrompt.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.getOne(loginId);
		if(login == null) {
			return false;
		}
		if(newPasswordPrompt.equals(login.getPasswordPrompt())) {
			return false;
		}
		String oldPasswordPrompt = login.getPasswordPrompt();
		login.setPasswordPrompt(newPasswordPrompt);
		Login save = loginDao.save(login);
		if(save != null && !save.getPasswordPrompt().equals(oldPasswordPrompt)) {
			return true;
		}
		return false;
	}

}
