package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 教师dao接口
 * 
 * @author liwei
 * 
 */
public interface TeacherDao extends JpaRepository<Teacher, Integer> {


}