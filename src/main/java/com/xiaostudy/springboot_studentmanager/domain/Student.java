package com.xiaostudy.springboot_studentmanager.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 学生domain类
 *
 * @author liwei
 *
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", length = 11)
	private Integer studentId;
    @Column(name = "student_number")
	private String studentNumber;
    @Column(name = "student_name")
	private String studentName;
    @Column(name = "sex")
	private String sex;
    @Column(name = "born")
	private Date born;
    @Column(name = "home")
	private String home;
    @Column(name = "home_name")
	private String homeName;
    @Column(name = "home_contact")
	private String homeContact;
    @Column(name = "admission_date")
	private Date admissionDate;
    @Column(name = "clazz_id")
	private Integer clazzId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeContact() {
        return homeContact;
    }

    public void setHomeContact(String homeContact) {
        this.homeContact = homeContact;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", born=" + born +
                ", home='" + home + '\'' +
                ", homeName='" + homeName + '\'' +
                ", homeContact='" + homeContact + '\'' +
                ", admissionDate=" + admissionDate +
                ", clazzId=" + clazzId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;
        if(studentId != null && student.studentId != null) {
            return Objects.equals(studentId, student.studentId) &&
                    Objects.equals(studentNumber, student.studentNumber) &&
                    Objects.equals(studentName, student.studentName) &&
                    Objects.equals(sex, student.sex) &&
                    Objects.equals(born, student.born) &&
                    Objects.equals(home, student.home) &&
                    Objects.equals(homeName, student.homeName) &&
                    Objects.equals(homeContact, student.homeContact) &&
                    Objects.equals(admissionDate, student.admissionDate) &&
                    clazzId.equals(student.clazzId);
        }
        return Objects.equals(studentNumber, student.studentNumber) &&
                Objects.equals(studentName, student.studentName) &&
                Objects.equals(sex, student.sex) &&
                Objects.equals(born, student.born) &&
                Objects.equals(home, student.home) &&
                Objects.equals(homeName, student.homeName) &&
                Objects.equals(homeContact, student.homeContact) &&
                Objects.equals(admissionDate, student.admissionDate) &&
                clazzId.equals(student.clazzId);
    }

}
