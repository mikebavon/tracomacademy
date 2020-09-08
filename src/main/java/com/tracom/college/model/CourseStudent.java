package com.tracom.college.model;

import com.tracom.base.BaseEntity;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class CourseStudent extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Formula("(course_id)")
    private int courseId;

    @Formula("(select c.name from courses c where c.id=course_id)")
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Formula("(student_id)")
    private int studentId;

    @Formula("(select concat(coalsece(c.first_name,''),' ',coalesce(c.last_name,'')) from students s where s.id=student_id)")
    private String studentName;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
