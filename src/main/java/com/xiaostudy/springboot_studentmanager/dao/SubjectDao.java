package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 学科dao接口
 * 
 * @author liwei
 * 
 */
public interface SubjectDao extends JpaRepository<Subject, Integer> {

}