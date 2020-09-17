package com.tracom.soap;

import com.tracom.college.model.Department;
import com.tracom.ejb.DepartmentBean;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;

@WebService
public class CourseSoapEndpoint {

    @EJB
    private DepartmentBean departmentBean;

    @WebMethod
    public List<Department> receiveNotification(@WebParam String name, @WebParam String location){

        Department filter = new Department();

        if (StringUtils.isNotBlank(name))
            filter.setName(StringUtils.trim(name));

        if (StringUtils.isNotBlank(location))
            filter.setLocation(StringUtils.trim(location));

        return departmentBean.list(filter);
    }

}
