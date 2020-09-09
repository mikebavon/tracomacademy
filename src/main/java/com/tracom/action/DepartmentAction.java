package com.tracom.action;

import com.tracom.college.model.Department;
import com.tracom.ejb.DepartmentBean;
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

@WebServlet("department")
public class DepartmentAction extends HttpServlet {

    @EJB
    private DepartmentBean departmentBean;

    @Inject
    private Department department;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String errorMsg = "";
        boolean success = true;

        try {
            BeanUtils.populate(department, request.getParameterMap());
            departmentBean.save(department);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;
        }


        if (success) {
            response.sendRedirect("department?action=redirected");

        }else {
            RequestDispatcher dispatcher;
            request.setAttribute("errorMsg", errorMsg);
            dispatcher = request.getRequestDispatcher("department/department_form.jsp");
            dispatcher.forward(request, response);
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean edit = false;

        try {
            BeanUtils.populate(department, request.getParameterMap());

            if (StringUtils.isNotBlank(department.getAction())){
                if(department.getId() != 0 && department.getAction().equalsIgnoreCase("delete")) {
                    departmentBean.delete(department.getId());

                }else if(department.getId() != 0 &&  department.getAction().equalsIgnoreCase("edit")) {
                    request.setAttribute("department", departmentBean.load(department.getId()));
                    edit = true;

                }else{
                    department = null;
                }
            }

            if (!edit)
                request.setAttribute("departments", departmentBean.list(department));

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
