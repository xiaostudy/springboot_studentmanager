package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 学生dao接口
 * 
 * @author liwei
 * 
 */
public interface StudentDao extends JpaRepository<Student, Integer> {

}