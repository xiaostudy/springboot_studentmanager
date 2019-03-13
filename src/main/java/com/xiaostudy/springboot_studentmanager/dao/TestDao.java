package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * 考试dao接口
 * 
 * @author liwei
 *
 */
public interface TestDao extends JpaRepository<Test, Integer> {
	
}