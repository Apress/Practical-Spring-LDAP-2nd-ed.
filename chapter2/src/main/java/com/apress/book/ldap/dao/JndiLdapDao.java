package com.apress.book.ldap.dao;

import com.apress.book.ldap.domain.Employee;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

public interface JndiLdapDao {
    void search();

    void addEmployee(Employee employee);

    void update(String dn, ModificationItem[] items);

    void remove(String dn);

    void removeSubtree(DirContext ctx, String root) throws NamingException;
}
