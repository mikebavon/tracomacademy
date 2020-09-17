package com.tracom.action;

import com.tracom.college.model.Department;
import com.tracom.college.model.Instructor;
import com.tracom.ejb.DepartmentBean;
import com.tracom.ejb.InstructorBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("instructor")
public class InstructorAction  extends HttpServlet {

    @EJB
    private DepartmentBean departmentBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String errorMsg = "";
        boolean success = true;

        Department department = new Department();
        try {
            BeanUtils.populate(department, request.getParameterMap());
            departmentBean.save(department);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;
        }


        if (success) {
            response.sendRedirect("department");

        }else {
            RequestDispatcher dispatcher;
            request.setAttribute("errorMsg", errorMsg);
            dispatcher = request.getRequestDispatcher("department/department_form.jsp");
            dispatcher.forward(request, response);
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean edit = false;

        Department filter = new Department();
        try {
            BeanUtils.populate(filter, request.getParameterMap());

            if (StringUtils.isNotBlank(filter.getAction()) && filter.getId() != 0){
                if(filter.getAction().equalsIgnoreCase("delete"))
                    departmentBean.delete(filter.getId());

                if(filter.getAction().equalsIgnoreCase("edit")) {
                    request.setAttribute("department", departmentBean.load(filter.getId()));
                    edit = true;
                }
            }

            if (!edit)
                request.setAttribute("departments", departmentBean.list(filter));

        }catch (Exception ex){
            request.setAttribute("departments", new ArrayList<Department>());
        }

        RequestDispatcher dispatcher;

        if (edit)
            dispatcher = request.getRequestDispatcher("department/department_form.jsp");
        else
            dispatcher = request.getRequestDispatcher("department/departments.jsp");

        dispatcher.forward(request, response);
    }

}