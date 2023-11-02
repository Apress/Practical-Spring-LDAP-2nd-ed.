package com.apress.book.ldap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class SupportedControlApplication {

    private static final Logger logger = LoggerFactory.getLogger(SupportedControlApplication.class);

    public void displayControls() {

        String ldapUrl = "ldap://localhost:11389";
        try {

            Properties environment = new Properties();
            environment.setProperty(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            environment.setProperty(DirContext.PROVIDER_URL, ldapUrl);

            DirContext context = new InitialDirContext(environment);
            Attributes attributes = context.getAttributes("", new String[] { "supportedcontrol" });

            Attribute supportedControlAttribute = attributes.get("supportedcontrol");
            NamingEnumeration controlOIDList = supportedControlAttribute.getAll();
            while (controlOIDList != null && controlOIDList.hasMore()) {
                logger.info(controlOIDList.next().toString());
            }

            context.close();
        } catch (NamingException e) {
            logger.error(e.getClass() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws NamingException {
        SupportedControlApplication supportedControlApplication = new SupportedControlApplication();
        supportedControlApplication.displayControls();
    }
}