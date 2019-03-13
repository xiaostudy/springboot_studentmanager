package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 班级dao接口
 * 
 * @author liwei
 *
 */


public interface ClazzDao extends JpaRepository<Clazz, Integer> {
	
}