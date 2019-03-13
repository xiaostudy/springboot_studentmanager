package com.xiaostudy.springboot_studentmanager.service;

import java.util.List;

import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.domain.Teacher;

/**
 * 班级service接口
 * 
 * @author liwei
 *
 */
public interface ClazzService {
	
	public List<Clazz> getClazzAll();
	
	public Clazz getClazzByClazzId(Integer clazzId);

	public Clazz getClazzByClazzNumber(String clazzNumber);

	public List<Clazz> getClazzByClazzName(String clazzName);
	
	public List<Clazz> getClazzByGradeName(String gradeName);
	
	public List<Clazz> getClazzByTeacherName(String teacherName);

	public List<String> getClazzNumberList();

	public List<String> getClazzNameList();

	public Grade getGrade(Integer clazzId);

	public Grade getGrade(Clazz clazz);

	public Teacher getTeacher(Integer clazzId);

	public Teacher getTeacher(Clazz clazz);

	public boolean deleteClazz(Clazz clazz);

	public boolean deleteClazz(String clazzNumber);

	public boolean insertClazz(Clazz clazz);
	
	public boolean updateClazz(Clazz clazz);
	
	public boolean isClazzInGradeName(String gradeName);

	public boolean isClazzInTeacherName(String teacherName);

	public boolean isClazzInGradeNameClazzName(String gradeName, String clazzName);

	public boolean isClazzInGradeNameTeacherName(String gradeName, String teacherName);

	public boolean isGradeName(String gradeName);

	public boolean isTeacherName(String teacherName);

	public boolean isClazz(Clazz clazz);

	public Clazz setClazzInTeacherName(Clazz clazz, String teacherName);

	public Clazz setClazzInGradeName(Clazz clazz, String gradeName);

	public Clazz setClazz(String clazzNumber, String clazzName, String gradeName, String teacherName);
}