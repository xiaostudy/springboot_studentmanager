package com.xiaostudy.springboot_studentmanager.service;

import java.util.List;

import com.xiaostudy.springboot_studentmanager.domain.Results;
import com.xiaostudy.springboot_studentmanager.domain.Student;
import com.xiaostudy.springboot_studentmanager.domain.Test;

/**
 * 成绩service接口
 * 
 * @author liwei
 *
 */
public interface ResultsService {
	
	public List<Results> getResultsAll();
	
	public Results getResultsByResultsId(Integer resultsId);
	
	public Results getResultsByResultsNumber(String resultsNumber);

	public List<Results> getResultsByStudentName(String gradeName, String studentName);

	public List<Results> getResultsByTestName(String gradeName, String testName);

	public List<Results> getResultsByStudentNameTestName(String gradeName, String studentName, String testName);

	public List<Results> getResultsBySubjectName(String gradeName, String subjectName);

	public List<Results> getResultsByStudentNameSubjectName(String gradeName, String studentName, String subjectName);

	public List<Results> getResultsByTestNameSubjectName(String gradeName, String testName, String subjectName);

	public Results getResultsByStudentNameTestNameSubjectName(String gradeName, String studentName, String testName, String subjectName);

	public List<Results> getResultsByScore(Integer score);

	public List<Integer> getResultsIdList();

	public List<String> getResultsNumberList();

	public Test getTest(Integer resultsId);

	public Test getTest(Results results);

	public Student getStudent(Integer resultsId);

	public Student getStudent(Results results);

	public boolean deleteResults(Results results);

	public boolean deleteResults(String resultsNumber);

	public boolean insert(Results results);
	
	public boolean updateResults(Results results);

	public boolean isStudentName(String studentName);

	public boolean isTestName(String testName);

	public boolean isSubjectName(String subjectName);

	public boolean isGradeName(String gradeName);

	public boolean isResultsInStudentName(String studentName);

	public boolean isResultsInTestName(String testName);

	public boolean isResultsInSubjectName(String subjectName);

	public boolean isResultsInGradeName(String gradeName);

	public Results setResults(String resultsNumber, String gradeName, String studentName, String testName, String subjectName, Integer score);
	
}