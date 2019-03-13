package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.ClazzDao;
import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.domain.Teacher;
import com.xiaostudy.springboot_studentmanager.service.ClazzService;
import com.xiaostudy.springboot_studentmanager.service.GradeService;
import com.xiaostudy.springboot_studentmanager.service.TeacherService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级service接口实现类
 * 
 * @author liwei
 *
 */
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzDao clazzDao;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private TeacherService teacherService;

	@Override
	public List<Clazz> getClazzAll() {
		List<Clazz> clazzAll = clazzDao.findAll();
		return clazzAll;
	}

	@Override
	public Clazz getClazzByClazzId(Integer clazzId) {
		if(clazzId == null) {
			return null;
		}

		return clazzDao.getOne(clazzId);
	}

	@Override
	public Clazz getClazzByClazzNumber(String clazzNumber) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0) {
			return null;
		}

        List<Clazz> clazzAll = getClazzAll();
		for(Clazz clazz : clazzAll) {
		    if(clazz.getClazzNumber().equals(clazzNumber)) {
		        return clazz;
            }
        }

        return null;
	}

	@Override
	public List<Clazz> getClazzByClazzName(String clazzName) {
		if(clazzName == null || clazzName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = new ArrayList<Clazz>();

        List<Clazz> clazzAll = getClazzAll();
        for(Clazz clazz : clazzAll) {
            if(clazz.getClazzName().equals(clazzName)) {
                clazzList.add(clazz);
            }
        }

		return clazzList;
	}

	@Override
	public List<Clazz> getClazzByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = new ArrayList<>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getGradeId() != null && gradeName.trim().equals(getGrade(clazz.getGradeId()).getGradeName())) {
				clazzList.add(clazz);
			}
		}

		return clazzList;
	}

	@Override
	public List<Clazz> getClazzByTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = new ArrayList<>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getTeacherId() != null && teacherName.trim().equals(getTeacher(clazz.getTeacherId()).getTeacherName())) {
				clazzList.add(clazz);
			}
		}

		return clazzList;
	}

	@Override
	public List<String> getClazzNumberList() {
		List<String> list = new ArrayList<String>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getClazzNumber() != null && clazz.getClazzNumber().trim().length() > 0) {
				list.add(clazz.getClazzNumber());
			}
		}

		return list;
	}

	@Override
	public List<String> getClazzNameList() {
		List<String> list = new ArrayList<String>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getClazzName() != null && clazz.getClazzName().trim().length() > 0) {
				list.add(clazz.getClazzName());
			}
		}

		return list;
	}

    @Override
    public Grade getGrade(Integer clazzId) {
	    if(clazzId == null) {
	        return null;
        }

        return getGrade(getClazzByClazzId(clazzId));
    }

    @Override
    public Grade getGrade(Clazz clazz) {
	    if(clazz == null || clazz.getGradeId() == null) {
	        return null;
        }

        return gradeService.getGradeByGradeId(clazz.getGradeId());
    }

    @Override
    public Teacher getTeacher(Integer clazzId) {
	    if(clazzId == null) {
	        return null;
        }

        return getTeacher(getClazzByClazzId(clazzId));
    }

    @Override
    public Teacher getTeacher(Clazz clazz) {
        if(clazz == null || clazz.getTeacherId() == null) {
            return null;
        }

        return teacherService.getTeacherByTeacherId(clazz.getTeacherId());
    }


	@Override
	public boolean deleteClazz(Clazz clazz) {
		if(clazz == null) {
			return false;
		}

		if(clazz.getClazzId() != null && getClazzByClazzId(clazz.getClazzId()) != null) {
			clazzDao.deleteById(clazz.getClazzId());
			if(clazzDao.existsById(clazz.getClazzId()) == false) {
			    return true;
            }
		}

		return deleteClazz(clazz.getClazzNumber());
	}

	@Override
	public boolean deleteClazz(String clazzNumber) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0) {
			return false;
		}

		if(getClazzByClazzNumber(clazzNumber.trim()) != null) {
            Clazz clazz = getClazzByClazzNumber(clazzNumber);
            if(clazz == null) {
                return false;
            }
            clazzDao.delete(clazz);
            clazz = getClazzByClazzNumber(clazzNumber);
			if(clazz == null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean insertClazz(Clazz clazz) {
		if(isClazz(clazz) == false) {
			return false;
		}

        Clazz save = clazzDao.save(clazz);
        if(save != null) {
            return true;
        }

		return false;
	}

	@Override
	public boolean updateClazz(Clazz clazz) {
		if(isClazz(clazz) == false) {
			return false;
		}

        Clazz save = clazzDao.save(clazz);
        if(!save.equals(clazz)) {
            return true;
        }

		return false;
	}

	@Override
	public boolean isClazzInGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return getClazzByGradeName(gradeName.trim()).size() > 0;
	}

	@Override
	public boolean isClazzInTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return false;
		}

		return getClazzByTeacherName(teacherName.trim()).size() > 0;
	}

	@Override
	public boolean isClazzInGradeNameClazzName(String gradeName, String clazzName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || clazzName == null || clazzName.trim().length() <= 0) {
			return false;
		}

		for(Clazz clazz : getClazzByGradeName(gradeName.trim())) {
			if(clazz != null && clazz.getClazzName().equals(clazzName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isClazzInGradeNameTeacherName(String gradeName, String teacherName) {
		if(isClazzInGradeName(gradeName) && teacherName != null && teacherName.trim().length() > 0) {
			List<Clazz> clazzList = getClazzByGradeName(gradeName.trim());
			for(Clazz clazz : clazzList) {
				if(clazz != null && clazz.getGradeId() != null && clazz.getTeacherId() != null && teacherName.trim().equals(getTeacher(clazz.getTeacherId()).getTeacherName())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		List<String> gradeNameList = gradeService.getGradeNameList();
		if(gradeNameList != null && gradeNameList.size() > 0){
			return gradeNameList.contains(gradeName.trim());
		}

		return false;
	}

	@Override
	public boolean isTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return false;
		}

		List<String> teacherNameList = teacherService.getTeacherNameList();
		if(teacherNameList != null && teacherNameList.size() > 0) {
			return teacherNameList.contains(teacherName.trim());
		}

		return false;
	}

	@Override
	public boolean isClazz(Clazz clazz) {
		if(clazz == null ||
				clazz.getClazzNumber() == null || clazz.getClazzNumber().trim().length() <= 0 ||
				clazz.getClazzName() == null || clazz.getClazzName().trim().length() <= 0 ||
				clazz.getGradeId() == null ||
				clazz.getTeacherId() == null) {
			return false;
		}

		return true;
	}

	@Override
	public Clazz setClazzInTeacherName(Clazz clazz, String teacherName) {
		if(clazz == null || teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		if(teacherService.getTeacherByTeacherName(teacherName.trim()) != null) {
			clazz.setTeacherId(teacherService.getTeacherByTeacherName(teacherName.trim()).getTeacherId());
			return clazz;
		}

		return null;
	}

	@Override
	public Clazz setClazzInGradeName(Clazz clazz, String gradeName) {
		if(clazz == null || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		if(gradeService.getGradeByGradeName(gradeName.trim()) != null) {
			clazz.setGradeId(gradeService.getGradeByGradeName(gradeName.trim()).getGradeId());
			return clazz;
		}

		return null;
	}

	@Override
	public Clazz setClazz(String clazzNumber, String clazzName, String gradeName, String teacherName) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0 ||
				clazzName == null || clazzName.trim().length() <= 0 ||
				gradeName == null || gradeName.trim().length() <= 0 ||
				teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		Grade grade = gradeService.getGradeByGradeName(gradeName.trim());
		Teacher teacher = teacherService.getTeacherByTeacherName(teacherName.trim());
		if(grade != null && teacher != null) {
			Clazz clazz = SpringUtil.getBean(Clazz.class);
			clazz.setClazzNumber(clazzNumber.trim());
			clazz.setClazzName(clazzName.trim());
			clazz.setGradeId(grade.getGradeId());
			clazz.setTeacherId(teacher.getTeacherId());
			return clazz;
		}

		return null;
	}

}
