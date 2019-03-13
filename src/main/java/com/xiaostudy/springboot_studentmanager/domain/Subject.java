package com.xiaostudy.springboot_studentmanager.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 学科domain类
 *
 * @author liwei
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", length = 11)
    private Integer subjectId;
    @Column(name = "subject_number")
    private String subjectNumber;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "grade_id")
    private Integer gradeId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectNumber='" + subjectNumber + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", gradeId=" + gradeId +
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

        Subject subject = (Subject) o;
        if (subjectId != null && subject.getSubjectId() != null) {
            return Objects.equals(subjectId, subject.subjectId) &&
                    Objects.equals(subjectNumber, subject.subjectNumber) &&
                    Objects.equals(subjectName, subject.subjectName) &&
                    gradeId.equals(subject.gradeId);
        }

        return Objects.equals(subjectNumber, subject.subjectNumber) &&
                Objects.equals(subjectName, subject.subjectName) &&
                gradeId.equals(subject.gradeId);
    }

}
