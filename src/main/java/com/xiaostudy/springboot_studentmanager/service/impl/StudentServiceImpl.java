package com.xiaostudy.springboot_studentmanager.service.impl;

import com.xiaostudy.springboot_studentmanager.dao.StudentDao;
import com.xiaostudy.springboot_studentmanager.domain.Clazz;
import com.xiaostudy.springboot_studentmanager.domain.Student;
import com.xiaostudy.springboot_studentmanager.service.ClazzService;
import com.xiaostudy.springboot_studentmanager.service.StudentService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生service接口实现类
 * 
 * @author liwei
 * 
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClazzService clazzService;

	@Override
	public List<Student> getStudentAll() {
		List<Student> studentAll = studentDao.findAll();
		return studentAll;
	}

	@Override
	public List<String> getStudentNameList() {
		List<String> list = new ArrayList<>();
		for(Student student : getStudentAll()) {
			if(student != null && student.getStudentName() != null && student.getStudentName().trim().length() > 0) {
				list.add(student.getStudentName());
			}
		}
		return list;
	}

	@Override
	public List<String> getStudentNameList(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || isGradeName(gradeName.trim()) == false) {
			return null;
		}

		List<String> list = new ArrayList<>();
		for(Student student : getStudentAll()) {
			if(student != null && student.getClazzId() != null && getClazz(student).getGradeId() != null && clazzService.getGrade(getClazz(student)).getGradeName().equals(gradeName.trim())) {
				list.add(student.getStudentName());
			}
		}

		return list;
	}

	@Override
	public Student getStudentByStudentId(Integer studentId) {
		if(studentId == null) {
			return null;
		}

		return studentDao.getOne(studentId);
	}

	@Override
	public Student getStudentByStudentNumber(String studentNumber) {
		if(studentNumber == null || studentNumber.trim().length() <= 0) {
			return null;
		}

		for(Student student : getStudentAll()) {
			if(student != null && student.getStudentNumber().equals(studentNumber.trim())) {
				return student;
			}
		}

		return null;
	}

	@Override
	public Student getStudentByStudentName(String studentName) {
		if(studentName == null || studentName.trim().length() <= 0) {
			return null;
		}

		for(Student student : getStudentAll()) {
			if(student != null && student.getStudentName().equals(studentName.trim())) {
				return student;
			}
		}

		return null;
	}

	@Override
	public List<Student> getStudentBySex(String sex) {
		if(sex == null || sex.trim().length() <= 0 || !sex.trim().matches("[男女]")) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getSex().equals(sex.trim())) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public List<Student> getStudentByBorn(Date born) {
		if(born == null) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getBorn().equals(born)) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public List<Student> getStudentByHome(String home) {
		if(home == null || home.trim().length() <= 0) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getHome().equals(home.trim())) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public List<Student> getStudentByHomeName(String homeName) {
		if(homeName == null || homeName.trim().length() <= 0) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getHomeName().equals(homeName.trim())) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public Student getStudentByHomeContact(String homeContact) {
		if(homeContact == null || homeContact.trim().length() <= 0) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getHomeContact().equals(homeContact.trim())) {
				return student;
			}
		}

		return null;
	}

	@Override
	public List<Student> getStudentByAdmissionDate(Date admissionDate) {
		if(admissionDate == null) {
			return null;
		}

		List<Student> list = new ArrayList<>();

		for(Student student : getStudentAll()) {
			if(student != null && student.getAdmissionDate().equals(admissionDate)) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public List<Student> getStudentByClazzName(String clazzName) {
		if(clazzName == null || clazzName.trim().length() <= 0) {
			return null;
		}

		List<Student> list = new ArrayList<>();
		for(Student student : getStudentAll()) {
			if(student != null && student.getClazzId() != null && getClazz(student).getClazzName().equals(clazzName.trim())) {
				list.add(student);
			}
		}

		return list;
	}

	@Override
	public List<String> getStudentNumberList() {
		List<String> list = new ArrayList<>();
		for(Student student : getStudentAll()) {
			if(student != null && student.getStudentNumber() != null && student.getStudentNumber().trim().length() > 0) {
				list.add(student.getStudentNumber());
			}
		}
		return list;
	}

	@Override
	public Clazz getClazz(Integer studentId) {
		if(studentId == null) {
			return null;
		}

		return getClazz(getStudentByStudentId(studentId));
	}

	@Override
	public Clazz getClazz(Student student) {
		if(student == null || student.getClazzId() == null) {
			return null;
		}

		return clazzService.getClazzByClazzId(student.getClazzId());
	}

	@Override
	public boolean deleteStudent(Student student) {
		if(student == null) {
			return false;
		}

		if(student.getStudentId() != null && studentDao.getOne(student.getStudentId()) != null) {
			studentDao.deleteById(student.getStudentId());
			if(studentDao.existsById(student.getStudentId()) == false) {
				return true;
			}
		}

		return deleteStudent(student.getStudentNumber());
	}

	@Override
	public boolean deleteStudent(String studentNumber) {
		if(studentNumber == null || studentNumber.trim().length() <= 0) {
			return false;
		}

		if(getStudentByStudentNumber(studentNumber.trim()) != null) {
			studentDao.delete(getStudentByStudentNumber(studentNumber.trim()));
			if(getStudentByStudentNumber(studentNumber.trim()) == null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean insertStudent(Student student) {
		if(student == null) {
			return false;
		}

		if(student.getStudentId() != null && studentDao.getOne(student.getStudentId()) == null) {
			Student save = studentDao.save(student);
			if(save != null) {
				return true;
			}
        }

        if(student.getStudentNumber() != null && getStudentByStudentNumber(student.getStudentNumber()) == null) {
			Student save = studentDao.save(student);
			if(save != null) {
				return true;
			}
        }

		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
	    if(student == null) {
	        return true;
        }

        if(student.getStudentId() != null && studentDao.getOne(student.getStudentId()) != null) {
			Student save = studentDao.save(student);
			if(!save.equals(student)) {
				return true;
			}
        }

        if(student.getStudentNumber() != null && getStudentByStudentNumber(student.getStudentNumber()) != null) {
			Student save = studentDao.save(student);
			if(!save.equals(student)) {
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

		return getStudentByStudentName(studentName.trim()) != null;
	}

    @Override
    public boolean isHomeContact(String homeContact) {
	    if(homeContact == null || homeContact.trim().length() <= 0) {
	        return false;
        }

        return getStudentByHomeContact(homeContact.trim()) != null;
    }

    @Override
    public boolean isGradeName(String gradeName) {
	    if(gradeName == null || gradeName.trim().length() <= 0) {
	        return false;
        }

        return clazzService.isGradeName(gradeName.trim());
    }

	@Override
	public boolean isClazzName(String clazzName) {
	    if(clazzName == null || clazzName.trim().length() <= 0) {
	        return false;
        }

        return clazzService.getClazzNameList().contains(clazzName.trim());
	}

	@Override
	public boolean isStudentInClazzName(String clazzName) {
	    if(clazzName == null || clazzName.trim().length() <= 0) {
	        return false;
        }

		return getStudentByClazzName(clazzName.trim()) != null;
	}

	@Override
	public boolean isStudentInStudentNameClazzName(String studentName, String clazzName) {
	    if(studentName == null || studentName.trim().length() <= 0 || clazzName == null || clazzName.trim().length() <= 0) {
	        return false;
        }

        Student student = getStudentByStudentName(studentName.trim());
	    if(student == null) {
	        return false;
        }

        return getClazz(student).getClazzName().equals(clazzName.trim());
	}

	@Override
	public Student setStudent(String studentNumber, String studentName, String sex, Date born, String home, String homeName, String homeContact, Date admissionDate, String gradeName, String clazzName) {
	    if(studentNumber == null || studentNumber.trim().length() <= 0||
                studentName == null || studentName.trim().length() <= 0 ||
                sex == null || sex.trim().length() <= 0 ||
                born == null ||
                home == null || home.trim().length() <= 0 ||
                homeName == null || homeName.trim().length() <= 0 ||
                homeContact == null || homeContact.trim().length() <= 0 ||
                admissionDate == null ||
                gradeName == null || gradeName.trim().length() <= 0 ||
                clazzName == null || clazzName.trim().length() <= 0) {
	        return null;
        }

	    Student student = SpringUtil.getBean(Student.class);
	    student.setStudentNumber(studentNumber.trim());
	    student.setStudentName(studentName.trim());
	    student.setSex(sex.trim());
	    student.setBorn(born);
	    student.setHome(home.trim());
	    student.setHomeName(homeName.trim());
	    student.setHomeContact(homeContact.trim());
	    student.setAdmissionDate(admissionDate);

		return setStudent(student, gradeName, clazzName);
	}

	@Override
	public Student setStudent(Student student, String gradeName, String clazzName) {
	    if(student == null || gradeName == null || gradeName.trim().length() <= 0 || clazzName == null || clazzName.trim().length() <= 0) {
	        return null;
        }

        List<Clazz> clazzList = clazzService.getClazzByClazzName(clazzName.trim());
        if(clazzList != null && clazzList.size() > 0) {
	        for(Clazz clazz : clazzList) {
	            if(clazz != null && clazzService.getGrade(getClazz(student)).getGradeName().equals(gradeName.trim())) {
	                student.setClazzId(clazz.getClazzId());
	                return student;
                }
            }
        }

		return null;
	}

}
