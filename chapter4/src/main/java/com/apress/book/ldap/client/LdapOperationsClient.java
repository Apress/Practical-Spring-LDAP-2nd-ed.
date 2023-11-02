package com.apress.book.ldap.client;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import com.apress.book.ldap.domain.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LdapOperationsClient {

    private LdapTemplate ldapTemplate;

    public LdapOperationsClient(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void add(String id, Patron patron) {
        // Set the Patron attributes
        Attributes attributes = new BasicAttributes();
        attributes.put("sn", patron.getLastName());
        attributes.put("givenName", patron.getFirstName());
        attributes.put("cn", patron.getCommonName());
        attributes.put("mail", patron.getEmail());

        // Add the multi-valued attribute
        BasicAttribute objectClassAttribute = new BasicAttribute("objectclass");
        objectClassAttribute.add("top");
        objectClassAttribute.add("person");
        objectClassAttribute.add("organizationalperson");
        objectClassAttribute.add("inetorgperson");
        attributes.put(objectClassAttribute);

        ldapTemplate.bind("uid=" + id + ",ou=patrons,dc=inflinx,dc=com", null, attributes);
    }

    public void updateEmail(String uid, String mail) {
        Attribute attribute = new BasicAttribute("mail", mail);
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
        ldapTemplate.modifyAttributes("uid=" + uid + ",ou=patrons,dc=inflinx,dc=com", new ModificationItem[] { item });
    }

    public void remove(String uid) {
        ldapTemplate.unbind("uid=" + uid + ",ou=patrons,dc=inflinx,dc=com");
    }

    public List<Patron> search() {
        return ldapTemplate.search("dc=inflinx,dc=com", "(objectclass=person)", (AttributesMapper) attributes -> {
            Patron patron = new Patron();
            patron.setUid((String) attributes.get("uid").get());
            patron.setFirstName((String) attributes.get("givenName").get());
            patron.setLastName((String) attributes.get("sn").get());
            patron.setEmail((String) attributes.get("mail").get());
            patron.setCommonName((String) attributes.get("cn").get());
            return patron;
        });
    }
}
