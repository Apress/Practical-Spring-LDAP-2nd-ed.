package com.apress.book.ldap.client;

import com.apress.book.ldap.domain.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("LDAP Operations Test using mocks")
class LdapOperationsClientMockTest {

    LdapOperationsClient client;
    LdapTemplate ldapTemplate;

    @BeforeEach
    public void setup() {
        ldapTemplate = mock(LdapTemplate.class);
        this.client = new LdapOperationsClient(ldapTemplate);
    }

    @Test
    @DisplayName("Check that operation search return a patron")
    void should_return_patrons() {
        Attributes attribute = new BasicAttributes();
        attribute.put("sn", "Sacco");
        attribute.put("givenName", "Andres");

        List<Attributes> attributes = new ArrayList<>();
        attributes.add(attribute);

        // Mock the response
        when(ldapTemplate.search(anyString(), anyString(), any(AttributesMapper.class))).thenReturn(attributes);

        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(1, patrons.size()));
    }
}
