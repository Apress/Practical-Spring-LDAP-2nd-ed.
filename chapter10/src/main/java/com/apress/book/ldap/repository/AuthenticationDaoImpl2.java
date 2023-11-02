package com.apress.book.ldap.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

@Repository("authenticationDao2")
public class AuthenticationDaoImpl2 implements AuthenticationDao {

    public static final String BASE_DN = "ou=patrons,dc=inflinx,dc=com";

    private LdapTemplate ldapTemplate;

    public AuthenticationDaoImpl2(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean authenticate(String userid, String password) {
        return ldapTemplate.authenticate(BASE_DN, "(uid=" + userid + ")", password);
    }
}
