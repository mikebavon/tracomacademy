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
public class InstructorAction extends HttpServlet {

    @EJB
    private InstructorBean instructorBean;

    @EJB
    private DepartmentBean departmentBean;

    @Inject
    private Instructor instructor;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String errorMsg = "";
        boolean success = true;

        try {
            BeanUtils.populate(instructor, request.getParameterMap());
            instructorBean.save(instructor);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;
        }


        if (success) {
            response.sendRedirect("instructor?action=redirected");

        }else {
            RequestDispatcher dispatcher;
            request.setAttribute("errorMsg", errorMsg);
            dispatcher = request.getRequestDispatcher("instructor/instructor_form.jsp");
            dispatcher.forward(request, response);
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean saveOrUpdate = false;

        try {
            BeanUtils.populate(instructor, request.getParameterMap());

            if (StringUtils.isNotBlank(instructor.getAction())){
                if (instructor.getAction().equalsIgnoreCase("redirected")){
                    instructor = null;

                }else if(instructor.getAction().equalsIgnoreCase("add")) {
                    instructor = null;
                    request.setAttribute("departments", departmentBean.list(new Department()));

                    saveOrUpdate = true;

                }else if(instructor.getId() != 0 && instructor.getAction().equalsIgnoreCase("delete")) {
                    instructorBean.delete(instructor.getId());

                }else if(instructor.getId() != 0 && instructor.getAction().equalsIgnoreCase("edit")) {
                    request.setAttribute("instructor", instructorBean.load(instructor.getId()));
                    request.setAttribute("departments", departmentBean.list(new Department()));

                    saveOrUpdate = true;

                }
            }

            if (!saveOrUpdate)
                request.setAttribute("instructors", instructorBean.list(instructor));

        }catch (Exception ex){
            request.setAttribute("instructors", new ArrayList<Instructor>());
        }

        RequestDispatcher dispatcher;

        if (saveOrUpdate)
            dispatcher = request.getRequestDispatcher("instructor/instructor_form.jsp");
        else
            dispatcher = request.getRequestDispatcher("instructor/instructors.jsp");

        dispatcher.forward(request, response);
    }


}