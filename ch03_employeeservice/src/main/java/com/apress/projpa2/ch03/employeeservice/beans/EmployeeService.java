package com.apress.projpa2.ch03.employeeservice.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.apress.projpa2.ch03.employeeservice.model.Employee;

public interface EmployeeService {

    public Employee createEmployee(int id, String name, long salary);
    public void removeEmployee(int id);
    public Employee changeEmployeeSalary(int id, long newSalary);
    public Employee findEmployee(int id);
    public List<Employee> findAllEmployees();
    public EntityManager getEntityManager();

}
