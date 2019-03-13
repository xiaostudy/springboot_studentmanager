package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.GradeDao;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 年级service接口实现类
 * 
 * @author liwei
 *
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;

	@Override
	public List<Grade> getGradeAll() {
		return gradeDao.findAll();
	}

	@Override
	public Grade getGradeByGradeId(Integer gradeId) {
		if(gradeId == null) {
			return null;
		}
		return gradeDao.getOne(gradeId);
	}

	@Override
	public Grade getGradeByGradeNumber(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return null;
		}

		List<Grade> gradeAll = getGradeAll();
		for(Grade grade : gradeAll) {
			if(grade != null && grade.getGradeNumber().equals(gradeNumber)) {
				return grade;
			}
		}
		return null;
	}

	@Override
	public Grade getGradeByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}
		List<Grade> gradeAll = getGradeAll();
		for(Grade grade : gradeAll) {
			if(grade != null && grade.getGradeName().equals(gradeName)) {
				return grade;
			}
		}
		return null;
	}

	@Override
	public List<String> getGradeNumberList() {
		List<Grade> gradeList = getGradeAll();
		List<String> list = new ArrayList<String>();
		if(gradeList == null || gradeList.size() <= 0) {
			return list;
		}
		for(Grade grade : gradeList) {
			if(grade != null && grade.getGradeNumber() != null) {
				list.add(grade.getGradeNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getGradeNameList() {
		List<Grade> gradeList = getGradeAll();
		List<String> list = new ArrayList<String>();
		if(gradeList == null || gradeList.size() <= 0) {
			return list;
		}

		for(Grade grade : gradeList) {
			if(grade != null && grade.getGradeName() != null && grade.getGradeName().trim().length() > 0) {
				list.add(grade.getGradeName());
			}
		}
		return list;
	}


	@Override
	public boolean deleteGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeId() != null && gradeDao.getOne(grade.getGradeId()) != null) {
			gradeDao.deleteById(grade.getGradeId());
			if(gradeDao.existsById(grade.getGradeId()) == false) {
				return true;
			}
		}

		return deleteGrade(grade.getGradeNumber());
	}

	@Override
	public boolean deleteGrade(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return false;
		}

		if(getGradeByGradeNumber(gradeNumber.trim()) != null) {
			gradeDao.delete(getGradeByGradeNumber(gradeNumber.trim()));
			if(getGradeByGradeNumber(gradeNumber.trim()) == null) {
				return true;
			}
        }

        return false;
	}

	@Override
	public boolean insertGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeNumber() == null || grade.getGradeName() == null) {
			return false;
		}

		Grade save = gradeDao.save(grade);
		if(save != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean updateGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeNumber() == null || grade.getGradeName() == null) {
			return false;
		}

		Grade save = gradeDao.save(grade);
		if(!save.equals(grade)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isGradeNumber(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return false;
		}

		Grade grade = getGradeByGradeNumber(gradeNumber);
		if(grade != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		Grade grade = getGradeByGradeName(gradeName.trim());
		if(grade != null) {
			return true;
		}

		return false;
	}

}
