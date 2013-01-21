package com.apress.projpa2.ch03.employeeservice.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.apress.projpa2.ch03.employeeservice.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {

    @PersistenceContext(unitName = "EmployeeService")
    protected EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Employee createEmployee(int id, String name, long salary) {
        Employee emp = new Employee(id);
        emp.setName(name);
        emp.setSalary(salary);
        getEntityManager().persist(emp);
        return emp;
    }

    @Override
    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    @Override
    public Employee changeEmployeeSalary(int id, long newSalary) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setSalary(newSalary);
        }
        return emp;
    }

    @Override
    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = getEntityManager().createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
