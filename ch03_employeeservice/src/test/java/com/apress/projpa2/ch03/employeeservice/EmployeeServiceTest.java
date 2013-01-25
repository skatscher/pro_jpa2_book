package com.apress.projpa2.ch03.employeeservice;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.apress.projpa2.ch03.employeeservice.beans.EmployeeService;
import com.apress.projpa2.ch03.employeeservice.model.Employee;

/**
 * Unit test for EmployeeServiceBean
 *
 * Run with Maven: $ mvn test -Parq-remote
 *
 */
@RunWith(Arquillian.class)
public class EmployeeServiceTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "employeeServiceTest.war").addPackages(true, "com.apress")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    protected Logger log;

    @Inject
    protected EmployeeService service;

    @Inject
    UserTransaction tx;

    @Test
    public void testCreateEmployee() throws Exception {
        tx.begin();
        Assert.assertNull(service.findEmployee(11));
        Employee employee = service.createEmployee(11, "Alice Smith", 10000);
        log.info("New {} created", employee);
        Assert.assertTrue("Employee should be created", service.getEntityManager().contains(employee));
        tx.rollback();
    }

    @Test
    public void testRemoveEmployee() throws Exception {
        tx.begin();
        Employee employee = service.createEmployee(12, "Tim Tango", 9000);
        log.info("Removing {}", employee);
        service.removeEmployee(employee.getId());
        Assert.assertFalse("Employee should be removed", service.getEntityManager().contains(employee));
        tx.rollback();
    }

    @Test
    public void testFindEmployee() throws Exception {
        tx.begin();
        Employee employee = service.createEmployee(13, "Sally Thunder", 13000);
        Employee employee2 = service.findEmployee(employee.getId());
        Assert.assertNotNull(employee2);
        Assert.assertEquals("Should find the expected employee", employee, employee2);
        tx.rollback();
    }

    @Test
    public void testFindAllEmployees() throws Exception {
        tx.begin();
        Employee employee1 = service.createEmployee(14, "Bob Hope", 17000);
        Employee employee2 = service.createEmployee(15, "Daniel Sinclair", 14000);
        List<Employee> allEmployees = service.findAllEmployees();
        Assert.assertEquals("Should find the expected number of employees", 2, allEmployees.size());
        Assert.assertTrue("Should find the expected employee", allEmployees.contains(employee1));
        Assert.assertTrue("Should find the expected employee", allEmployees.contains(employee2));
        tx.rollback();
    }

    @Test
    public void testChangeEmployeeSalary() throws Exception {
        tx.begin();
        Employee employee = service.createEmployee(16, "Mario Mirelli", 18000);
        long new_salary = 18500;
        employee = service.changeEmployeeSalary(employee.getId(), new_salary);
        Assert.assertEquals(new_salary, employee.getSalary());
        tx.rollback();
    }

}
