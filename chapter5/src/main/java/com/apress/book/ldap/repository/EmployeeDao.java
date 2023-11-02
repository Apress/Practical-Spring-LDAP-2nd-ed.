package com.apress.book.ldap.repository;

import java.util.List;

import com.apress.book.ldap.domain.Employee;

public interface EmployeeDao {

    void create(Employee employee);

    void update(Employee employee);

    void delete(String id);

    Employee find(String id);

    List<Employee> findAll();
}
