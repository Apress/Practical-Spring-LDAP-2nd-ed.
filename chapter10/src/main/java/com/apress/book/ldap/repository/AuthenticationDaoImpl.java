package com.apress.book.ldap.repository;

import javax.naming.directory.DirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

@Repository("authenticationDao")
public class AuthenticationDaoImpl implements AuthenticationDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationDaoImpl.class);
    public static final String BASE_DN = "ou=patrons,dc=inflinx,dc=com";

    private ContextSource contextSource;

    public AuthenticationDaoImpl(@Autowired @Qualifier("contextSource") ContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Override
    public boolean authenticate(String userid, String password) {

        DistinguishedName dn = new DistinguishedName(BASE_DN);
        dn.add("uid", userid);

        DirContext authenticatedContext = null;
        try {
            authenticatedContext = contextSource.getContext(dn.toString(), password);
            return Boolean.TRUE;
        } catch (NamingException ex) {
            logger.error("{}: {}", ex.getClass(), ex.getMessage());
            return Boolean.FALSE;
        } finally {
            LdapUtils.closeContext(authenticatedContext);
        }
    }
}