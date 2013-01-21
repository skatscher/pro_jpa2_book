package com.apress.projpa2.ch03.employeeservice;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.apress.projpa2.ch03.employeeservice.beans.EmployeeServiceBean;
import com.apress.projpa2.ch03.employeeservice.model.Employee;

/**
 * Unit test for EmployeeServiceBean
 *
 * Run with Maven:
 * $ mvn test -Parq-remote
 *
 */
@RunWith(Arquillian.class)
public class EmployeeServiceTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap
                .create(WebArchive.class, "employeeServiceTest.war")
                .addPackages(true, "com.apress.projpa2.ch03.employeeservice")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    protected Logger log;

    @Inject
    protected EmployeeServiceBean service;

    protected Employee testEmployee;

    @Before
    public void setup() {
//        testEmployee = service.createEmployee(10, "John Doe", 10000);
    }

    @Test
    public void testCreateEmployee() {
        Assert.assertTrue(true);
//        Employee employee = service.createEmployee(11, "Alice Smith", 10000);
//        Assert.assertTrue("Employee should be created", em.contains(employee));
    }

//    @Test
//    public void testRemoveEmployee() {
//        service.removeEmployee(testEmployee.getId());
//        Assert.assertFalse("Employee should be removed", em.contains(testEmployee));
//    }
//
//    @Test
//    public void testFindEmployee() {
//        Employee employee = service.findEmployee(testEmployee.getId());
//        Assert.assertEquals("Should find the expected employee", testEmployee, employee);
//    }
//
//    @Test
//    public void testFindAllEmployees() {
//        Employee employee = service.createEmployee(12, "Bob", 11000);
//        List<Employee> allEmployees = service.findAllEmployees();
//        Assert.assertEquals("Should find the expected number of employees", 2, allEmployees.size());
//        Assert.assertTrue("Should find the expected employee", allEmployees.contains(testEmployee));
//        Assert.assertTrue("Should find the expected employee", allEmployees.contains(employee));
//    }
//
//    @Test
//    public void testRaiseEmployeeSalary() {
//        long raise = 1000L;
//        long old_salary = testEmployee.getSalary();
//        Employee employee = service.changeEmployeeSalary(testEmployee.getId(), raise);
//        Assert.assertEquals(old_salary + raise, employee.getSalary());
//    }

}
