package com.apress.book.ldap;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

public class SearchClient {
    private static final Logger logger = LoggerFactory.getLogger(SearchClient.class);

    @SuppressWarnings("unchecked")
    public List<String> search() {
        LdapTemplate ldapTemplate = getLdapTemplate();

        return ldapTemplate.search("dc=inflinx,dc=com", "(objectclass=person)",
                (AttributesMapper) attributes -> (String) attributes.get("cn").get());
    }

    // Do this only for check how works but try to delegate the creation of the context source to Spring
    private LdapTemplate getLdapTemplate() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:11389");
        contextSource.setUserDn("cn=Directory Manager");
        contextSource.setPassword("secret");
        try {
            contextSource.afterPropertiesSet();
        } catch (Exception e) {
            logger.error(e.getClass() + ": " + e.getMessage());
        }

        LdapTemplate ldapTemplate = new LdapTemplate();
        ldapTemplate.setContextSource(contextSource);

        return ldapTemplate;
    }

    public static void main(String[] args) {
        SearchClient client = new SearchClient();
        List<String> names = client.search();

        for (String name : names) {
            System.out.println(name);
        }
    }
}
