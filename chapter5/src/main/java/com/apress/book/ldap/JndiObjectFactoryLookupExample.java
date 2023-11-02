package com.apress.book.ldap;

import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.apress.book.ldap.domain.Patron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JndiObjectFactoryLookupExample {

    private static final Logger logger = LoggerFactory.getLogger(JndiObjectFactoryLookupExample.class);

    private LdapContext getContext() throws NamingException {
        Properties environment = new Properties();
        environment.setProperty(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.setProperty(DirContext.PROVIDER_URL, "ldap://localhost:11389");
        environment.setProperty(DirContext.SECURITY_PRINCIPAL, "cn=Directory Manager");
        environment.setProperty(DirContext.SECURITY_CREDENTIALS, "secret");
        environment.setProperty(DirContext.OBJECT_FACTORIES, "com.apress.book.ldap.PatronObjectFactory");

        return new InitialLdapContext(environment, null);
    }

    public Patron lookupPatron(String dn) {
        Patron patron = null;

        try {
            LdapContext context = getContext();
            patron = (Patron) context.lookup(dn);
        } catch (NamingException e) {
            logger.error(e.getClass() + ": " + e.getMessage());
        }
        return patron;
    }

    public static void main(String[] args) {
        JndiObjectFactoryLookupExample jle = new JndiObjectFactoryLookupExample();
        Patron p = jle.lookupPatron("uid=patron99,ou=patrons,dc=inflinx,dc=com");
        logger.info(p.toString());
    }
}
