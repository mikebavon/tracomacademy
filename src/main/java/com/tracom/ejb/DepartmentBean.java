package com.tracom.ejb;

import com.tracom.college.model.Department;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class DepartmentBean {

    @PersistenceContext
    private EntityManager em;

    public Department save(Department department) throws Exception{
        if (department == null || StringUtils.isBlank(department.getName()))
            throw new Exception("Invalid department details");

        return em.merge(department);

    }

    public Department load(int departmentId) throws Exception{
        if (departmentId == 0)
            return new Department();

        Department department = em.find(Department.class, departmentId);

        if (department == null)
            return new Department();

        return department;

    }

    @SuppressWarnings({"unchecked"})
    public List<Department> list(Department filter){
        String hql = "SELECT d FROM Department d WHERE d.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(filter.getName()))
                hql += " AND d.name like '%" + StringUtils.trim(filter.getName()) + "%'";

            if (StringUtils.isNotBlank(filter.getLocation()))
                hql += " AND d.location like '%" + StringUtils.trim(filter.getLocation()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }

    public void delete(int departmentId) throws Exception{
        if (departmentId == 0)
            throw new Exception("Invalid department Id..");

        em.remove(em.find(Department.class, departmentId));
    }

}
