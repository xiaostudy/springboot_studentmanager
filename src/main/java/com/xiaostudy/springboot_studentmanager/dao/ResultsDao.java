package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 成绩dao接口
 * 
 * @author liwei
 *
 */
public interface ResultsDao extends JpaRepository<Results, Integer> {
	
}