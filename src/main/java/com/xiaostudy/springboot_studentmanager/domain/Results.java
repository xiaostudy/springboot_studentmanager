package com.xiaostudy.springboot_studentmanager.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 成绩domain类
 * 
 * @author liwei
 *
 */
@Entity
@Table(name = "results")
public class Results implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "results_id", length = 11)
	private Integer resultsId;
	@Column(name = "results_number")
	private String resultsNumber;
	@Column(name = "student_id")
	private Integer studentId;
	@Column(name = "test_id")
	private Integer testId;
	@Column(name = "score")
	private Integer score;

	public Integer getResultsId() {
		return resultsId;
	}

	public void setResultsId(Integer resultsId) {
		this.resultsId = resultsId;
	}

	public String getResultsNumber() {
		return resultsNumber;
	}

	public void setResultsNumber(String resultsNumber) {
		this.resultsNumber = resultsNumber;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Results{" +
				"resultsId=" + resultsId +
				", resultsNumber='" + resultsNumber + '\'' +
				", studentId=" + studentId +
				", testId=" + testId +
				", score=" + score +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}

		if (o == null || getClass() != o.getClass()) {return false;}

		Results results = (Results) o;
		if(resultsId != null && results.resultsId != null) {
			return Objects.equals(resultsId, results.resultsId) &&
					Objects.equals(resultsNumber, results.resultsNumber) &&
					studentId.equals(results.studentId) &&
					testId.equals(results.testId) &&
					Objects.equals(score, results.score);
		}

		return Objects.equals(resultsNumber, results.resultsNumber) &&
				studentId.equals(results.studentId) &&
				testId.equals(results.testId) &&
				Objects.equals(score, results.score);
	}

}
