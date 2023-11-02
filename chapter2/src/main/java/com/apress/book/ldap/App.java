package com.apress.book.ldap;

import com.apress.book.ldap.dao.impl.JndiLdapDaoImpl;
import com.apress.book.ldap.domain.Employee;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

public class App {
    public static void main(String[] args) {
        JndiLdapDaoImpl jli = new JndiLdapDaoImpl();
        // Search
        jli.search();

        // Add and search
        jli.addEmployee(getEmployee());
        jli.search();

        // Remove and search
        jli.remove("uid=employee29,ou=employees,dc=inflinx,dc=com");
        jli.search();

        // Update and search
        BasicAttribute attribute = new BasicAttribute("givenName", "Andy");
        ModificationItem[] items = { new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute) };
        jli.update("uid=employee30,ou=employees,dc=inflinx,dc=com", items);
        jli.search();
    }

    private static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeNumber("50001");
        employee.setFirstName("Andres");
        employee.setLastName("Sacco");
        employee.setEmail("sacco.andres@gmail.com");

        employee.setUid("employee30");
        employee.setPhone(new String[] { "+54 9 1161484" });
        employee.setCommonName("Andres");
        return employee;
    }
}
