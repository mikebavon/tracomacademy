package com.tracom.college.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class DepartmentWrapper implements Serializable {

    public DepartmentWrapper(){}

    public DepartmentWrapper(List<Department> departments){
        this.departments = departments;

    }

    List<Department> departments = new ArrayList<Department>();

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
