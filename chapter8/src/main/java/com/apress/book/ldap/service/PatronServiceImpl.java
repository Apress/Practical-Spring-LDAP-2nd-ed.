package com.apress.book.ldap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import com.apress.book.ldap.domain.Patron;

@Service("patronService")
public class PatronServiceImpl implements PatronService {

    private static final String PATRON_BASE = "ou=patrons,dc=inflinx,dc=com";

    private LdapTemplate ldapTemplate;

    public PatronServiceImpl(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public void create(Patron patron) {
        ldapTemplate.create(patron);
    }

    @Override
    public void update(Patron patron) {
        ldapTemplate.update(patron);
    }

    @Override
    public Patron find(String id) {
        DistinguishedName dn = new DistinguishedName(PATRON_BASE);
        dn.add("uid", id);
        return ldapTemplate.findByDn(dn, Patron.class);
    }

    @Override
    public void delete(String id) {
        ldapTemplate.delete(find(id));
    }
}