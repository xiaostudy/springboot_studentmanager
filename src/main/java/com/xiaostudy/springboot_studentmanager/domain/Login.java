package com.xiaostudy.springboot_studentmanager.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 登录domain类
 * 
 * @author liwei
 *
 */
@Entity
@Table(name = "login")
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loginId;
	@Column(name = "position")
	private String position;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "password_prompt")
	private String passwordPrompt;

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordPrompt() {
		return passwordPrompt;
	}

	public void setPasswordPrompt(String passwordPrompt) {
		this.passwordPrompt = passwordPrompt;
	}

	@Override
	public String toString() {
		return "Login{" +
				"loginId=" + loginId +
				", position='" + position + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", passwordPrompt='" + passwordPrompt + '\'' +
				'}';
	}
}
