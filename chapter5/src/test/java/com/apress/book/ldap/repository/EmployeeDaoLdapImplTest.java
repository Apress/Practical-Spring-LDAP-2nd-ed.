package com.apress.book.ldap.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.test.context.ContextConfiguration;

import com.apress.book.ldap.domain.Employee;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("Employee Dao test cases")
class EmployeeDaoLdapImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @DisplayName("Check that operation return all the employees")
    void should_return_all_employees() {
        List<Employee> employeeList = employeeDao.findAll();
        assertFalse(employeeList.isEmpty());
    }

    @Test
    @DisplayName("Check that operation return one specific employee")
    void should_return_a_valid_employee() {
        Employee employee = employeeDao.find("employee1");
        assertNotNull(employee);
    }

    @Test
    @DisplayName("Check that operation create one employee")
    void should_create_a_new_employee() {
        String empUid = "employee1000";

        Employee employee = new Employee();
        employee.setUid(empUid);
        employee.setFirstName("Test");
        employee.setLastName("Employee1000");
        employee.setCommonName("Test Employee1000");
        employee.setEmail("employee1000@inflinx.com");
        employee.setEmployeeNumber("45678");
        employee.setPhone(new String[] { "801-100-1200" });

        employeeDao.create(employee);

        employee = employeeDao.find(empUid);
        assertNotNull(employee);
    }

    @Test
    @DisplayName("Check that operation update one employee")
    void should_update_an_employee() {
        Employee employee = employeeDao.find("employee1");
        employee.setFirstName("Employee New");
        employeeDao.update(employee);

        employee = employeeDao.find("employee1");
        assertEquals("Employee New", employee.getFirstName());
    }

    @Test
    @DisplayName("Check that operation remove one employee")
    void should_delete_an_employee() {
        String empUid = "employee11";
        employeeDao.delete(empUid);
        assertThrows(NameNotFoundException.class, () -> {
            employeeDao.find(empUid);
        });
    }
}
