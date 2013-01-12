package com.apress.projpa2.ch02.employeeservice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EmployeeService service;
    private static Employee testEmployee;

    @BeforeClass
    public static void beforeClass() {
        emf = Persistence.createEntityManagerFactory("EmployeeService");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void afterClass() {
        em.close();
        emf.close();
    }

    @Before
    public void setup() {
        service = new EmployeeService(em);
        testEmployee = service.createEmployee(10, "John Doe", 10000);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = service.createEmployee(11, "Alice Smith", 10000);
        Assert.assertTrue("Employee should be created", em.contains(employee));
    }

    @Test
    public void testRemoveEmployee() {
        service.removeEmployee(testEmployee.getId());
        Assert.assertFalse("Employee should be removed", em.contains(testEmployee));
    }

    @Test
    public void testFindEmployee() {
        Employee employee = service.findEmployee(testEmployee.getId());
        Assert.assertEquals("Should find the expected employee", testEmployee, employee);
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee = service.createEmployee(12, "Bob", 11000);
        List<Employee> allEmployees = service.findAllEmployees(); // FIXME JPQL Query finds nothing without commit
        Assert.assertEquals("Should find the expected number of employees", 2, allEmployees.size());
        Assert.assertTrue("Should find the expected employee", allEmployees.contains(testEmployee));
        Assert.assertTrue("Should find the expected employee", allEmployees.contains(employee));
    }

    @Test
    public void testRaiseEmployeeSalary() {
        long raise = 1000L;
        long old_salary = testEmployee.getSalary();
        Employee employee = service.raiseEmployeeSalary(testEmployee.getId(), raise);
        Assert.assertEquals(old_salary + raise, employee.getSalary());
    }

}
