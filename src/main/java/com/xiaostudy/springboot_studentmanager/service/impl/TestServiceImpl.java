package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.TestDao;
import com.xiaostudy.springboot_studentmanager.domain.Subject;
import com.xiaostudy.springboot_studentmanager.domain.Test;
import com.xiaostudy.springboot_studentmanager.service.SubjectService;
import com.xiaostudy.springboot_studentmanager.service.TestService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试service接口实现类
 * 
 * @author liwei
 *
 */
@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Autowired
	private SubjectService subjectService;

	@Override
	public List<Test> getTestAll() {
		List<Test> testAll = testDao.findAll();

		return testAll;
	}

	@Override
	public Test getTestByTestId(Integer testId) {
		if(testId == null) {
			return null;
		}

		return testDao.getOne(testId);
	}

	@Override
	public Test getTestByTestNumber(String testNumber) {
		if(testNumber == null || testNumber.trim().length() <= 0) {
			return null;
		}

		for(Test test : getTestAll()) {
			if(test != null && test.getTestNumber().equals(testNumber.trim())) {
				return test;
			}
		}

		return null;
	}

	@Override
	public List<Test> getTestByTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Test> testList = new ArrayList<Test>();
		for(Test test : getTestAll()) {
			if(test != null && test.getTestName().equals(testName.trim())) {
				testList.add(test);
			}
		}

		return testList;
	}

	@Override
	public List<Test> getTestByGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Test> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && subjectService.getGrade(getSubject(test)).getGradeName().equals(gradeName.trim()) && getSubject(test).getSubjectName().equals(subjectName.trim())) {
				list.add(test);
			}
		}
		return list;
	}

	@Override
	public List<String> getTestNameByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && getSubject(test).getGradeId() != null && subjectService.getGrade(getSubject(test)).getGradeName().equals(gradeName.trim()) && list.contains(test.getTestName()) == false) {
				list.add(test.getTestName());
			}
		}

		return list;
	}

	@Override
	public List<String> getSubjectNameByGradeNameTestName(String gradeName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				testName == null || testName.trim().length() <= 0 ||
				isGradeName(gradeName.trim()) == false || isTestName(testName.trim()) == false) {
			return null;
		}

		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && getSubject(test).getGradeId() != null && subjectService.getGrade(getSubject(test)).getGradeName().equals(gradeName.trim()) && test.getTestName().equals(testName.trim())) {
				list.add(getSubject(test).getSubjectName());
			}
		}

		return list;
	}

	@Override
	public List<String> getTestNumberList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getTestNumber() != null && test.getTestNumber().trim().length() > 0) {
				list.add(test.getTestNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getTestNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getTestName() != null && test.getTestName().trim().length() > 0 && list.contains(test.getTestName()) == false) {
				list.add(test.getTestName());
			}
		}
		return list;
	}

	@Override
	public List<String> getSubjectNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && getSubject(test).getSubjectName() != null && getSubject(test).getSubjectName().trim().length() > 0) {
				list.add(getSubject(test).getSubjectName());
			}
		}

		return list;
	}

	@Override
	public List<String> getGradeNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && getSubject(test).getGradeId() != null && list.contains(subjectService.getGrade(getSubject(test)).getGradeName()) == false) {
				list.add(subjectService.getGrade(getSubject(test)).getGradeName());
			}
		}

		return list;
	}

	@Override
	public Subject getSubject(Integer testId) {
		if(testId == null) {
			return null;
		}

		return getSubject(getTestByTestId(testId));
	}

	@Override
	public Subject getSubject(Test test) {
		if(test == null || test.getSubjectId() == null) {
			return null;
		}

		return subjectService.getSubjectBySubjectId(test.getSubjectId());
	}

	@Override
	public boolean deleteTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) != null) {
			testDao.deleteById(test.getTestId());
			if(testDao.existsById(test.getTestId()) == false) {
				return true;
			}
		}

		return deleteTest(test.getTestNumber());
	}

	@Override
	public boolean deleteTest(String testNumber) {
		if(testNumber == null || testNumber.trim().length() <= 0) {
			return false;
		}

		if(getTestByTestNumber(testNumber.trim()) != null) {
			testDao.delete(getTestByTestNumber(testNumber));
			if(getTestByTestNumber(testNumber) == null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean insertTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) == null &&
				test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) == null &&
				test.getSubjectId() != null && isSubjectName(getSubject(test).getSubjectName())) {
			Test save = testDao.save(test);
			if(save != null) {
				return true;
			}
		}

		if(test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) == null &&
				test.getSubjectId() != null && isSubjectName(getSubject(test).getSubjectName())) {
			Test save = testDao.save(test);
			if(save != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) != null) {
			Test save = testDao.save(test);
			if(!save.equals(test)) {
				return true;
			}
		}

		if(test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) != null) {
			Test save = testDao.save(test);
			if(!save.equals(test)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		return getTestNameList().contains(testName.trim());
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return subjectService.getGrade(gradeName.trim()) != null;
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		return subjectService.getSubjectNameList().contains(subjectName.trim());
	}

	@Override
	public boolean isTestInGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		for(Test test : getTestAll()) {
			if(test != null && test.getSubjectId() != null && getSubject(test).getSubjectName().equals(subjectName.trim()) && subjectService.getGrade(getSubject(test)).getGradeName().equals(gradeName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isTestNameGradeNameSubjectName(String testName, String gradeName, String subjectName) {
		if(testName == null || testName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		List<Test> testList = getTestByTestName(testName.trim());
		if(testList != null && testList.size() > 0) {
			for(Test test : testList) {
				if(test != null && test.getSubjectId() != null && getSubject(test).getSubjectName().equals(subjectName.trim()) && getSubject(test).getGradeId() != null && subjectService.getGrade(getSubject(test)).getGradeName().equals(gradeName.trim())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Test setTest(String testNumber, String testName, String gradeName, String subjectName) {
		if(testNumber == null || testNumber.trim().length() <= 0 || testName == null || testName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		Test test = SpringUtil.getBean(Test.class);
		test.setTestNumber(testNumber.trim());
		test.setTestName(testName.trim());
		return setTest(test, gradeName, subjectName);
	}

	@Override
	public Test setTest(Test test, String gradeName, String subjectName) {
		if(test == null || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		Subject subject = subjectService.getSubjectByGradeNameSubjectName(gradeName.trim(), subjectName.trim());
		if(subject != null) {
			test.setSubjectId(subject.getSubjectId());
			return test;
		}

		return null;
	}
}
