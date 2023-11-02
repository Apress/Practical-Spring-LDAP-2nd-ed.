package com.apress.book.ldap.mapper;

import com.apress.book.ldap.domain.Patron;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

public class PatronContextMapper extends AbstractContextMapper<Patron> {
    @Override
    protected Patron doMapFromContext(DirContextOperations context) {
        Patron patron = new Patron();

        patron.setUid(context.getStringAttribute("uid"));
        patron.setFirstName(context.getStringAttribute("givenName"));
        patron.setLastName(context.getStringAttribute("sn"));
        patron.setFullName(context.getStringAttribute("cn"));
        patron.setEmail(context.getStringAttribute("mail"));

        return patron;
    }
}
