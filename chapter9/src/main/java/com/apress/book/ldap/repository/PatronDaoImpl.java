package com.apress.book.ldap.repository;

import com.apress.book.ldap.domain.Patron;
import com.apress.book.ldap.mapper.PatronContextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("patronDao")
@Transactional
public class PatronDaoImpl implements PatronDao {

    private static final Logger logger = LoggerFactory.getLogger(PatronDaoImpl.class);

    private static final String PATRON_BASE = "ou=patrons,dc=inflinx,dc=com";

    private LdapTemplate ldapTemplate;

    public PatronDaoImpl(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public void create(Patron patron) {
        logger.info("Inside the create method ...");
        DistinguishedName dn = new DistinguishedName(PATRON_BASE);
        dn.add("uid", patron.getUid());

        DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues("objectClass",
                new String[] { "top", "uidObject", "person", "organizationalPerson", "inetOrgPerson" });
        context.setAttributeValue("sn", patron.getLastName());
        context.setAttributeValue("cn", patron.getFullName());

        ldapTemplate.bind(context);
    }

    @Override
    public void delete(String id) {
        DistinguishedName dn = new DistinguishedName(PATRON_BASE);
        dn.add("uid", id);
        ldapTemplate.unbind(dn);
    }

    @Override
    public void update(Patron patron) {
        DistinguishedName dn = new DistinguishedName(PATRON_BASE);
        dn.add("uid", patron.getUid());

        DirContextOperations context = ldapTemplate.lookupContext(dn);
        context.setAttributeValues("objectClass",
                new String[] { "top", "uidObject", "person", "organizationalPerson", "inetOrgPerson" });
        context.setAttributeValue("sn", patron.getLastName());
        context.setAttributeValue("cn", patron.getFullName());

        ldapTemplate.modifyAttributes(context);

    }

    @Override
    public Patron find(String id) {
        EqualsFilter filter = new EqualsFilter("uid", id);
        return ldapTemplate.searchForObject(PATRON_BASE, filter.encode(), new PatronContextMapper());
    }

}
