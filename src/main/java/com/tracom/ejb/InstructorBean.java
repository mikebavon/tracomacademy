package com.tracom.ejb;

import com.tracom.college.model.Department;
import com.tracom.college.model.Instructor;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class InstructorBean  {

    @PersistenceContext
    private EntityManager em;

    public Instructor save(Instructor instructor) throws Exception{
        if (instructor == null || StringUtils.isBlank(instructor.getIdNo()))
            throw new Exception("Invalid instructor details");

        if (instructor.getDepartmentId() != 0)
            instructor.setDepartment(em.getReference(Department.class, instructor.getDepartmentId()));

        return em.merge(instructor);

    }

    public Instructor load(int instructorId) throws Exception{
        if (instructorId == 0)
            return new Instructor();

        Instructor instructor = em.find(Instructor.class, instructorId);

        if (instructor == null)
            return new Instructor();

        return instructor;

    }

    @SuppressWarnings({"unchecked"})
    public List<Instructor> list(Instructor filter) throws Exception{
        String hql = "SELECT i FROM Instructor i WHERE i.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(filter.getIdNo()))
                hql += " AND i.idNo like '%" + StringUtils.trim(filter.getIdNo()) + "%'";

            if (StringUtils.isNotBlank(filter.getFirstName()))
                hql += " AND i.firstName like '%" + StringUtils.trim(filter.getFirstName()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }

    public void delete(int instructorId) throws Exception{
        if (instructorId == 0)
            throw new Exception("Invalid department Id..");

        em.remove(em.find(Instructor.class, instructorId));
    }

}