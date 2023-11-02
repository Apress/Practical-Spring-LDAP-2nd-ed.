package com.apress.book.ldap.repository.mapper;

import com.apress.book.ldap.domain.Employee;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

public class EmployeeContextMapper extends AbstractContextMapper<Employee> {
    @Override
    protected Employee doMapFromContext(DirContextOperations context) {
        Employee employee = new Employee();

        employee.setUid(context.getStringAttribute("uid"));
        employee.setFirstName(context.getStringAttribute("givenName"));
        employee.setLastName(context.getStringAttribute("sn"));
        employee.setCommonName(context.getStringAttribute("cn"));
        employee.setEmployeeNumber(context.getStringAttribute("employeeNumber"));
        employee.setEmail(context.getStringAttribute("mail"));
        employee.setPhone(context.getStringAttributes("telephoneNumber"));

        return employee;
    }
}
