package com.apress.book.ldap.repository;

import com.apress.book.ldap.exception.EmployeeAuthenticationErrorCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

@Repository("authenticationDao3")
public class AuthenticationDaoImpl3 implements AuthenticationDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationDaoImpl3.class);

    public static final String BASE_DN = "ou=patrons,dc=inflinx,dc=com";

    private LdapTemplate ldapTemplate;

    public AuthenticationDaoImpl3(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean authenticate(String userid, String password) {
        EmployeeAuthenticationErrorCallback errorCallback = new EmployeeAuthenticationErrorCallback();
        boolean isAuthenticated = ldapTemplate.authenticate(BASE_DN, "(uid=" + userid + ")", password, errorCallback);
        if (!isAuthenticated) {
            logger.info(errorCallback.getAuthenticationException().getMessage());
        }
        return isAuthenticated;
    }
}
