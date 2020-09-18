package com.tracom.rest;

import com.tracom.college.model.Department;
import com.tracom.college.model.DepartmentWrapper;
import com.tracom.ejb.DepartmentBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/department")
public class DepartmentRestApi {

    @EJB
    private DepartmentBean departmentBean;

    @GET
    @Path("/list/{location}")
    @Produces(MediaType.APPLICATION_XML)
    public Response list(@PathParam("location") String location){

        if (location != null && location.equalsIgnoreCase("all"))
            location = null;

        Department filter = new Department();
        filter.setLocation(location);
        return Response.status(200).entity(new DepartmentWrapper(departmentBean.list(filter))).build();

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Department department){
        try {
            departmentBean.save(department);
        }catch (Exception ex){
            return "{\"error\":\"" + ex.getMessage() + "\"}";
        }

        return "{\"success\":\"OK\"}";
    }

}
