package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录dao接口
 * 
 * @author liwei
 *
 */
//@Transactional
public interface LoginDao extends JpaRepository<Login, Integer> {
	
}