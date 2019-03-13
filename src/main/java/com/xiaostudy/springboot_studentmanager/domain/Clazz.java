package com.xiaostudy.springboot_studentmanager.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 班级domain类
 *
 * @author liwei
 */

/**
 * //使用JPA注解配置映射关系
 * @Entity //告诉JPA这是一个实体类（和数据表映射的类）
 * @Table(name = "tbl_user") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
 * public class User {
 *
 *     @Id //这是一个主键
 *     @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
 *     private Integer id;
 *
 *     @Column(name = "last_name",length = 50) //这是和数据表对应的一个列
 *     private String lastName;
 *     @Column //省略默认列名就是属性名
 *     private String email;
 */
@Entity
@Table(name = "clazz")
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id//这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name = "clazz_id", length = 11)//说明id名称，可以不要
    private Integer clazzId;
    @Column(name = "clazz_number",length = 40) //这是和数据表对应的一个列
    private String clazzNumber;
    @Column(name = "clazz_name")
    private String clazzName;
    @Column(name = "grade_id")
    private Integer gradeId;
    @Column(name = "teacher_id")
    private Integer teacherId;

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzId=" + clazzId +
                ", clazzNumber='" + clazzNumber + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", gradeId=" + gradeId +
                ", teacherId=" + teacherId +
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

        Clazz clazz = (Clazz) o;
        if(clazzId != null && clazz.clazzId != null) {
            return Objects.equals(clazzId, clazz.clazzId) &&
                    Objects.equals(clazzNumber, clazz.clazzNumber) &&
                    Objects.equals(clazzName, clazz.clazzName) &&
                    gradeId.equals(clazz.gradeId) &&
                    teacherId.equals(clazz.teacherId);
        }
        return Objects.equals(clazzNumber, clazz.clazzNumber) &&
                Objects.equals(clazzName, clazz.clazzName) &&
                gradeId.equals(clazz.gradeId) &&
                teacherId.equals(clazz.teacherId);
    }

}
