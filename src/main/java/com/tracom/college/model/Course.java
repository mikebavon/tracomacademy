package com.tracom.college.model;

import com.tracom.base.BaseEntity;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column
    private String name;

    @Column
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Formula("(department_id)")
    private int departmentId;

    @Formula("(select d.name from departments d where d.id=department_id)")
    private String departmentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
