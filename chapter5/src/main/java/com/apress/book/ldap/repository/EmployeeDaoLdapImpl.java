package com.apress.book.ldap.repository;

import java.util.List;

import com.apress.book.ldap.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import com.apress.book.ldap.repository.mapper.EmployeeContextMapper;

@Repository("employeeDao")
public class EmployeeDaoLdapImpl implements EmployeeDao {

    private LdapTemplate ldapTemplate;

    public EmployeeDaoLdapImpl(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    @Override
    public List<Employee> findAll() {
        return ldapTemplate.search("", "(objectClass=inetOrgPerson)", new EmployeeContextMapper());
    }

    @Override
    public Employee find(String id) {
        DistinguishedName dn = getDistinguishedName(id);
        return ldapTemplate.lookup(dn, new EmployeeContextMapper());
    }

    @Override
    public void create(Employee employee) {
        DistinguishedName dn = getDistinguishedName(employee.getUid());

        DirContextAdapter context = new DirContextAdapter();
        context.setDn(dn);
        context.setAttributeValues("objectClass",
                new String[] { "top", "person", "organizationalperson", "inetorgperson" });
        context.setAttributeValue("givenName", employee.getFirstName());
        context.setAttributeValue("sn", employee.getLastName());
        context.setAttributeValue("cn", employee.getCommonName());
        context.setAttributeValue("mail", employee.getEmail());
        context.setAttributeValue("employeeNumber", employee.getEmployeeNumber());
        context.setAttributeValues("telephoneNumber", employee.getPhone());

        ldapTemplate.bind(context);
    }

    @Override
    public void update(Employee employee) {
        DistinguishedName dn = getDistinguishedName(employee.getUid());

        DirContextOperations context = ldapTemplate.lookupContext(dn);

        context.setAttributeValues("objectClass",
                new String[] { "top", "person", "organizationalperson", "inetorgperson" });
        context.setAttributeValue("givenName", employee.getFirstName());
        context.setAttributeValue("sn", employee.getLastName());
        context.setAttributeValue("cn", employee.getCommonName());
        context.setAttributeValue("mail", employee.getEmail());
        context.setAttributeValue("employeeNumber", employee.getEmployeeNumber());
        context.setAttributeValues("telephoneNumber", employee.getPhone());

        ldapTemplate.modifyAttributes(context);
    }

    @Override
    public void delete(String id) {
        DistinguishedName dn = getDistinguishedName(id);
        ldapTemplate.unbind(dn);
    }

    private DistinguishedName getDistinguishedName(String id) {
        DistinguishedName dn = new DistinguishedName();
        dn.add("uid", id);
        return dn;
    }

}
