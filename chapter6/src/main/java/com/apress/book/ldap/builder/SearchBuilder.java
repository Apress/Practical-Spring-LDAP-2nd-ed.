package com.apress.book.ldap.builder;

import com.apress.book.ldap.domain.Patron;
import com.apress.book.ldap.mapper.PatronContextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component("searchBuilder")
public class SearchBuilder {
    private static final Logger logger = LoggerFactory.getLogger(SearchBuilder.class);
    private LdapTemplate ldapTemplate;

    public SearchBuilder(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<Patron> getPatronByLastNameAndPostalCode(String lastName, String postalCode) {
        logger.info("finding patrons with lastname {} and postalcode {}", lastName, postalCode);
        LdapQuery query = query().countLimit(10).base(LdapUtils.emptyLdapName()).where("objectclass").is("person")
                .and("sn").is(lastName).and("postalCode").is(postalCode).and("uid").isPresent();

        return ldapTemplate.search(query, new PatronContextMapper());
    }
}
