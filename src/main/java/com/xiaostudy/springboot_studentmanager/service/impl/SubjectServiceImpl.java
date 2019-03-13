package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.SubjectDao;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.domain.Subject;
import com.xiaostudy.springboot_studentmanager.service.GradeService;
import com.xiaostudy.springboot_studentmanager.service.SubjectService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 学科service接口实现类
 * 
 * @author liwei
 * 
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private GradeService gradeService;

	@Override
	public List<Subject> getSubjectAll() {
		return subjectDao.findAll();
	}

	@Override
	public List<Integer> getPages() {
		List<Integer> list = new ArrayList<>();
		int j = 1;
		for(int i = 0; i < getSubjectAll().size(); i+=5) {
			list.add(j);
			j++;
		}
		return list;
	}

	@Override
	public List<Subject> getSubjectPages(Integer i) {
		if(i == null) {
			return null;
		}

		List<Subject> list = new ArrayList<Subject>();

		List<Subject> subjectAll = getSubjectAll();

		for(int j = (i-1)*5, k = 0; k < 5; k++) {
			list.add(subjectAll.get(j));
		}

		return list;
	}

	@Override
	public Subject getSubjectBySubjectId(Integer subjectId) {
		if(subjectId == null) {
			return null;
		}
		return subjectDao.getOne(subjectId);
	}

	@Override
	public Subject getSubjectBySubjectNumber(String subjectNumber) {
		if(subjectNumber == null) {
			return null;
		}

		List<Subject> subjectAll = getSubjectAll();
		for(Subject subject : subjectAll) {
			if(subject != null && subject.getSubjectNumber().equals(subjectNumber.trim())) {
				return subject;
			}
		}

		return null;
	}

	@Override
	public List<Subject> getSubjectBySubjectName(String subjectName) {
		if (subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Subject> list = new ArrayList<>();
		for(Subject subject : getSubjectAll()) {
			if(subject != null && subject.getSubjectName().equals(subjectName.trim())) {
				list.add(subject);
			}
		}

		return list;
	}

	@Override
	public List<Subject> getSubjectByGradeNumber(String gradeNumber){
		List<Subject> subjectList = new ArrayList<>();
		if(gradeNumber == null) {
			return subjectList;
		}

		List<Subject> list = new ArrayList<>();
		for(Subject subject : getSubjectAll()) {
			if(subject != null && getGrade(subject.getGradeId()).getGradeNumber().equals(gradeNumber.trim())) {
				list.add(subject);
			}
		}

		return list;
	}

	@Override
	public List<Subject> getSubjectByGradeName(String gradeName) {
		List<Subject> subjectList = new ArrayList<>();
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return subjectList;
		}

		for(Subject subject : getSubjectAll()) {
			if(subject != null && getGrade(subject.getGradeId()).getGradeName().equals(gradeName.trim())) {
				subjectList.add(subject);
			}
		}

		return subjectList;
	}

	@Override
	public Subject getSubjectByGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Subject> subjectList = getSubjectBySubjectName(subjectName.trim());
		for(Subject subject : subjectList) {
			if(subject != null && gradeName.trim().equals(getGrade(subject.getGradeId()).getGradeName())) {
				return subject;
			}
		}

		return null;
	}

	@Override
	public List<String> getSubjectNumberList() {
		List<Subject> subjectAll = getSubjectAll();
		List<String> list = new ArrayList<String>();
		for(Subject subject : subjectAll) {
			if(subject != null && subject.getSubjectNumber() != null) {
				list.add(subject.getSubjectNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getSubjectNameList() {
		List<Subject> subjectAll = getSubjectAll();
		List<String> list = new ArrayList<String>();
		for(Subject subject : subjectAll) {
			if(subject != null && subject.getSubjectName() != null && subject.getSubjectName().trim().length() > 0) {
				list.add(subject.getSubjectName());
			}
		}
		return list;
	}

	@Override
	public Grade getGrade(Integer subjectId) {
		if(subjectId == null) {
			return null;
		}

		return getGrade(getSubjectBySubjectId(subjectId));
	}

	@Override
	public Grade getGrade(Subject subject) {
		if(subject == null || subject.getGradeId() == null) {
			return null;
		}

		return gradeService.getGradeByGradeId(subject.getGradeId());
	}

	@Override
	public boolean deleteSubject(Subject subject){
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectId() != null && subjectDao.getOne(subject.getSubjectId()) != null) {
			subjectDao.deleteById(subject.getSubjectId());
			if(subjectDao.existsById(subject.getSubjectId()) == false) {
				return true;
			}
		}

		return deleteSubject(subject.getSubjectNumber());
	}

	@Override
	public boolean deleteSubject(String subjectNumber) {
		if(subjectNumber == null || subjectNumber.trim().length() <= 0) {
			return false;
		}

		if(getSubjectBySubjectNumber(subjectNumber.trim()) != null) {
			subjectDao.delete(getSubjectBySubjectNumber(subjectNumber.trim()));
			if(getSubjectBySubjectNumber(subjectNumber.trim()) == null) {
			    return true;
            }
		}

		return false;
	}
	
	@Override
	public boolean insertSubject(Subject subject) {
		if (subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGradeId() == null) {
			return false;
		}

        Grade grade = getGrade(subject);
        if(subject.getSubjectId() != null && subjectDao.getOne(subject.getSubjectId()) == null) {
            if(grade != null && grade.getGradeId() != null && gradeService.getGradeByGradeId(grade.getGradeId()) != null) {
                Subject save = subjectDao.save(subject);
                if(save != null) {
                    return true;
                }
			}
			if(grade.getGradeNumber() != null && gradeService.getGradeByGradeNumber(grade.getGradeNumber()) != null) {
				subject.setGradeId(gradeService.getGradeByGradeNumber(grade.getGradeNumber()).getGradeId());
                Subject save = subjectDao.save(subject);
                if(save != null) {
                    return true;
                }
			}
		}

		if(subject.getSubjectNumber() != null && getSubjectBySubjectNumber(subject.getSubjectNumber()) == null) {
			if(grade.getGradeId() != null && gradeService.getGradeByGradeId(grade.getGradeId()) != null) {
                Subject save = subjectDao.save(subject);
                if(save != null) {
                    return true;
                }
			}
			if(grade.getGradeNumber() != null && gradeService.getGradeByGradeNumber(grade.getGradeNumber()) != null) {
				subject.setGradeId(gradeService.getGradeByGradeNumber(grade.getGradeNumber()).getGradeId());
                Subject save = subjectDao.save(subject);
                if(save != null) {
                    return true;
                }
			}
		}

		return false;
	}

	@Override
	public boolean updateSubject(Subject subject) {
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGradeId() == null) {
			return false;
		}

		if(subject.getSubjectId() != null && subjectDao.getOne(subject.getSubjectId()) != null) {
            Subject save = subjectDao.save(subject);
            if(!save.equals(subject)) {
                return true;
            }
		}

		if(subject.getSubjectNumber() != null && getSubjectBySubjectNumber(subject.getSubjectNumber()) != null) {
            Subject save = subjectDao.save(subject);
            if(!save.equals(subject)) {
                return true;
            }
		}

		return false;
	}

	@Override
	public boolean isSubjectNumber(String subjectNumbeer) {
		if(subjectNumbeer == null) {
			return false;
		}

		Subject subject = getSubjectBySubjectNumber(subjectNumbeer);
		if(subject != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		List<Subject> subjectList = getSubjectBySubjectName(subjectName.trim());
		if(subjectList != null && subjectList.size() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		Grade grade = gradeService.getGradeByGradeName(gradeName.trim());
		if(grade != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectNameInGradeName(String subjectName, String gradeName) {
		if(subjectName == null || subjectName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		List<Subject> subjectList = getSubjectByGradeName(gradeName.trim());
		for(Subject subject : subjectList) {
			if(subject != null && subjectName.trim().equals(subject.getSubjectName())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Subject setSubjectInGrade(String subjectNumber, String subjectName, String gradeName) {
		if(subjectNumber == null || subjectName == null || subjectName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		Subject subject = SpringUtil.getBean(Subject.class);
		subject.setSubjectNumber(subjectNumber);
		subject.setSubjectName(subjectName.trim());
		return setSubjectInGradeToGradeName(subject, gradeName);
	}

	@Override
	public Subject setSubjectInGradeToGradeName(Subject subject, String gradeName) {
		if(subject == null || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		if(gradeService.getGradeByGradeName(gradeName.trim()) == null) {
			return null;
		}

		subject.setGradeId(gradeService.getGradeByGradeName(gradeName.trim()).getGradeId());
		return subject;
	}

	@Override
	public boolean equals(Subject oldSubject, Subject newSubject) {
		if(isSubjectToNULL(oldSubject) == false || isSubjectToNULL(newSubject) == false) {
			return false;
		}

		if (oldSubject.getSubjectNumber().equals(newSubject.getSubjectNumber()) && oldSubject.getSubjectName().equals(newSubject.getSubjectName()) && oldSubject.getGradeId().equals(newSubject.getGradeId())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectToNULL(Subject subject) {
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGradeId() == null) {
			return false;
		}

		return true;
	}

	@Override
	public Grade getGrade(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		return gradeService.getGradeByGradeName(gradeName.trim());
	}

}
