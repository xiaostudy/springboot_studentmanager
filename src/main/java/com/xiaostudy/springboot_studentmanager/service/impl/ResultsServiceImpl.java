package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.ResultsDao;
import com.xiaostudy.springboot_studentmanager.domain.Results;
import com.xiaostudy.springboot_studentmanager.domain.Student;
import com.xiaostudy.springboot_studentmanager.domain.Test;
import com.xiaostudy.springboot_studentmanager.service.*;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 成绩service接口实现类
 * 
 * @author liwei
 *
 */
@Service("resultsService")
public class ResultsServiceImpl implements ResultsService {

	@Autowired
	private ResultsDao resultsDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TestService testService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ClazzService clazzService;

	@Override
	public List<Results> getResultsAll() {
		List<Results> list = resultsDao.findAll();
		return list;
	}

	@Override
	public Results getResultsByResultsId(Integer resultsId) {
		if(resultsId == null) {
			return null;
		}

		return resultsDao.getOne(resultsId);
	}

	@Override
	public Results getResultsByResultsNumber(String resultsNumber) {
		if(resultsNumber == null || resultsNumber.trim().length() <= 0) {
			return null;
		}

		for(Results results : getResultsAll()) {
			if(results != null && results.getResultsNumber().equals(resultsNumber.trim())) {
				return results;
			}
		}

		return null;
	}

	@Override
	public List<Results> getResultsByStudentName(String gradeName, String studentName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					testService.getSubject(getTest(results)).getSubjectName().equals(studentName.trim()) &&
					subjectService.getGrade(testService.getSubject(getTest(results))).getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsByTestName(String gradeName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					getTest(results).getTestName().equals(testName.trim()) &&
					subjectService.getGrade(testService.getSubject(getTest(results))).getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}
		return list;
	}

	@Override
	public List<Results> getResultsByStudentNameTestName(String gradeName, String studentName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					getTest(results).getTestName().equals(testName.trim()) &&
					subjectService.getGrade(testService.getSubject(getTest(results))).getGradeName().equals(gradeName.trim()) &&
					clazzService.getGrade(studentService.getClazz(getStudent(results))).getGradeName().equals(gradeName.trim()) &&
					getStudent(results).getStudentName().equals(studentName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsBySubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					testService.getSubject(getTest(results)).getSubjectName().equals(subjectName.trim()) &&
					subjectService.getGrade(testService.getSubject(getTest(results))).getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}
		return list;
	}

	@Override
	public List<Results> getResultsByStudentNameSubjectName(String gradeName, String studentName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					getStudent(results).getStudentName().equals(studentName.trim()) &&
					clazzService.getGrade(studentService.getClazz(getStudent(results))).getGradeName().equals(gradeName.trim()) &&
					subjectService.getGrade(getTest(results).getSubjectId()).getGradeName().equals(gradeName.trim()) &&
					subjectService.getSubjectBySubjectId(getTest(results).getSubjectId()).getSubjectName().equals(subjectName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsByTestNameSubjectName(String gradeName, String testName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					getTest(results).getTestName().equals(testName.trim()) &&
					subjectService.getGrade(getTest(results).getSubjectId()).getGradeName().equals(gradeName.trim()) &&
					subjectService.getSubjectBySubjectId(getTest(results).getSubjectId()).getSubjectName().equals(subjectName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public Results getResultsByStudentNameTestNameSubjectName(String gradeName, String studentName, String testName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				studentName == null || studentName.trim().length() <= 0 || 
				testName == null || testName.trim().length() <= 0 || 
				subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && results.getTestId() != null &&
					getStudent(results).getStudentName().equals(studentName.trim()) &&
					clazzService.getGrade(studentService.getClazz(getStudent(results))).getGradeName().equals(gradeName.trim()) &&
					getTest(results).getTestName().equals(testName.trim()) &&
					subjectService.getGrade(getTest(results).getSubjectId()).getGradeName().equals(gradeName.trim()) &&
					subjectService.getSubjectBySubjectId(getTest(results).getSubjectId()).getSubjectName().equals(subjectName.trim())) {
				return results;
			}
		}

		return null;
	}

	@Override
	public List<Results> getResultsByScore(Integer score) {
		if(score == null) {
			return null;
		}

		List<Results> list = new ArrayList<Results>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getScore().equals(score)) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Integer> getResultsIdList() {
		List<Integer> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			list.add(results.getResultsId());
		}

		return list;
	}

	@Override
	public List<String> getResultsNumberList() {
		List<String> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			list.add(results.getResultsNumber());
		}

		return list;
	}

	@Override
	public Test getTest(Integer resultsId) {
		if(resultsId == null) {
			return null;
		}

		return getTest(getResultsByResultsId(resultsId));
	}

	@Override
	public Test getTest(Results results) {
		if(results == null || results.getTestId() == null) {
			return null;
		}

		return testService.getTestByTestId(results.getTestId());
	}

	@Override
	public Student getStudent(Integer resultsId) {
		if(resultsId == null) {
			return null;
		}

		return getStudent(getResultsByResultsId(resultsId));
	}

	@Override
	public Student getStudent(Results results) {
		if(results == null || results.getStudentId() == null) {
			return null;
		}

		return studentService.getStudentByStudentId(results.getStudentId());
	}

	@Override
	public boolean deleteResults(Results results) {
		if(results == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) != null) {
			resultsDao.deleteById(results.getResultsId());
			if(resultsDao.existsById(results.getResultsId()) == false) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) != null) {
			return deleteResults(results.getResultsNumber());
		}

		return false;
	}

	@Override
	public boolean deleteResults(String resultsNumber) {
		if(resultsNumber == null || resultsNumber.trim().length() <= 0) {
			return false;
		}
		resultsDao.delete(getResultsByResultsNumber(resultsNumber.trim()));
		if(getResultsByResultsNumber(resultsNumber.trim()) == null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean insert(Results results) {
		if(results == null || results.getStudentId() == null || results.getTestId() == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) == null) {
			Results save = resultsDao.save(results);
			if(save != null) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) == null) {
			Results save = resultsDao.save(results);
			if(save != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateResults(Results results) {
		if(results == null || results.getStudentId() == null || results.getTestId() == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) != null) {
			Results save = resultsDao.save(results);
			if(save != null) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) != null) {
			Results save = resultsDao.save(results);
			if(save != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isStudentName(String studentName) {
		if(studentName == null || studentName.trim().length() <= 0) {
			return false;
		}

		return studentService.isStudentName(studentName.trim());
	}

	@Override
	public boolean isTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		return testService.isTestName(testName.trim());
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		return subjectService.isSubjectName(subjectName.trim());
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return subjectService.isGradeName(gradeName.trim());
	}

	@Override
	public boolean isResultsInStudentName(String studentName) {
		if(studentName == null || studentName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && results.getStudentId() != null && getStudent(results).getStudentName().equals(studentName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && results.getTestId() != null && getTest(results).getTestName().equals(testName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && getTest(results).getSubjectId() != null && testService.getSubject(getTest(results)).getSubjectName().equals(subjectName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if(results != null && results.getStudentId() != null && getStudent(results).getClazzId() != null && studentService.getClazz(getStudent(results)).getGradeId() != null && clazzService.getGrade(studentService.getClazz(getStudent(results))).getGradeName().equals(gradeName.trim())) {
				return true;
			}
		}

		return false;
	}


	@Override
	public Results setResults(String resultsNumber, String gradeName, String studentName, String testName, String subjectName, Integer score) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				resultsNumber == null || resultsNumber.trim().length() <= 0 ||
				studentName == null || studentName.trim().length() <= 0 ||
				testName == null || testName.trim().length() <= 0 ||
				subjectName == null || subjectName.trim().length() <= 0 || score == null) {
			return null;
		}

		Results results = SpringUtil.getBean(Results.class);
		results.setResultsNumber(resultsNumber.trim());
		results.setScore(score);
		Student student = studentService.getStudentByStudentName(studentName.trim());
		System.out.println(student);
		if(student != null && clazzService.getGrade(studentService.getClazz(getStudent(results))).getGradeName().equals(gradeName.trim())) {
			results.setStudentId(student.getStudentId());
		} else {
			return null;
		}

		for(Test test : testService.getTestByTestName(testName.trim())) {
			if(test != null && test.getSubjectId() != null && testService.getSubject(getTest(results)).getSubjectName().equals(subjectName.trim()) && subjectService.getGrade(testService.getSubject(getTest(results))).getGradeName().equals(gradeName.trim())) {
				results.setTestId(test.getTestId());
				return results;
			}
		}

		return null;
	}
}
