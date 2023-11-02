package com.apress.book.ldap.client;

import com.apress.book.ldap.domain.Patron;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("LDAP Operations Test using an embedded LDAP")
class LdapOperationsClientEmbeddedTest {

    @Autowired
    LdapOperationsClient client;

    @Test
    @DisplayName("Check that operation search return a patron")
    void should_return_patrons() {
        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(100, patrons.size()));
    }

    @Test
    @DisplayName("Check that operation remove works")
    void should_remove_patron() {
        client.remove("patron100");

        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(100, patrons.size()));
    }

    @Test
    @DisplayName("Check that operation add create a new entry on the LDAP")
    void should_add_patron() {
        Patron patron = new Patron(null, "Andres", "Sacco", "Andres Sacco", "sacco.andres@inflix.com");
        client.add("patron102", patron);

        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(101, patrons.size()));
    }

}
