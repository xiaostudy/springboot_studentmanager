package com.xiaostudy.springboot_studentmanager.dao;

import com.xiaostudy.springboot_studentmanager.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 年级dao接口
 * 
 * @author liwei
 * 
 */
public interface GradeDao  extends JpaRepository<Grade, Integer> {
}