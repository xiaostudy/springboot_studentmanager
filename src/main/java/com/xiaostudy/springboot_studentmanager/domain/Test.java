package com.xiaostudy.springboot_studentmanager.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 考试domain类
 *
 * @author liwei
 */
@Entity
@Table(name = "test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", length = 11)
    private Integer testId;
    @Column(name = "test_number", length = 11)
    private String testNumber;
    @Column(name = "test_name", length = 11)
    private String testName;
    @Column(name = "subject_id", length = 11)
    private Integer subjectId;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", testNumber='" + testNumber + '\'' +
                ", testName='" + testName + '\'' +
                ", subjectId=" + subjectId +
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

        Test test = (Test) o;
        if (testId != null && test.testId != null) {
            return Objects.equals(testId, test.testId) &&
                    Objects.equals(testNumber, test.testNumber) &&
                    Objects.equals(testName, test.testName) &&
                    subjectId.equals(test.subjectId);
        }

        return Objects.equals(testNumber, test.testNumber) &&
                Objects.equals(testName, test.testName) &&
                subjectId.equals(test.subjectId);
    }

}
