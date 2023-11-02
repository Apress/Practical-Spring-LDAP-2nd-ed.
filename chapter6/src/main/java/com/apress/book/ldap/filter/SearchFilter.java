package com.apress.book.ldap.filter;

import java.util.List;

import com.apress.book.ldap.domain.Patron;
import com.apress.book.ldap.mapper.PatronContextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Component;

@Component("searchFilter")
public class SearchFilter {
    private static final Logger logger = LoggerFactory.getLogger(SearchFilter.class);

    private LdapTemplate ldapTemplate;

    public SearchFilter(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<Patron> searchAndPrintResults(Filter filter) {
        List<Patron> results = ldapTemplate.search("ou=patrons,dc=inflinx,dc=com", filter.encode(),
                new PatronContextMapper());

        logger.info("Results found in search: " + results.size());
        for (Patron patron : results) {
            logger.info(patron.toString());
        }
        return results;
    }
}