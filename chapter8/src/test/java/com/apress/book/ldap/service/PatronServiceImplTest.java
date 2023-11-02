package com.apress.book.ldap.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.test.context.ContextConfiguration;

import com.apress.book.ldap.domain.Patron;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("Patron Service test cases")
class PatronServiceImplTest {

    @Autowired
    private PatronService patronService;

    @Test
    @DisplayName("Check that operation add create a new entry on the LDAP")
    void should_create_a_patron() {
        Patron patron = new Patron();
        patron.setDn(new DistinguishedName("uid=patron10001,ou=patrons,dc=inflinx,dc=com"));
        patron.setFirstName("Patron");
        patron.setLastName("Test 1");
        patron.setFullName("Patron Test 1");
        patron.setMail("balaji@inflinx.com");
        patron.setEmployeeNumber(1234);
        patron.setTelephoneNumber("8018640759");

        patronService.create(patron);

        // Lets read the patron
        Patron result = patronService.find("patron10001");
        assertAll(() -> assertNotNull(result),
                () -> assertEquals(patron.getDn().toString(), result.getDn().toString()));
    }

    @Test
    @DisplayName("Check that operation search return a patron")
    void should_find_a_patron() {
        Patron result = patronService.find("patron100");
        assertAll(() -> assertNotNull(result),
                () -> assertEquals("uid=patron100,ou=patrons,dc=inflinx,dc=com", result.getDn().toString()));
    }

    @Test
    @DisplayName("Check that operation remove works")
    void should_delete_a_patron() {
        patronService.delete("patron92");

        assertThrows(NameNotFoundException.class, () -> patronService.find("patron92"));
    }

    @Test
    @DisplayName("Check that operation update an entry on the LDAP")
    void should_update_a_patron() {
        Patron patron = patronService.find("patron1");
        assertNotNull(patron);

        patron.setTelephoneNumber("8018640850");
        patronService.update(patron);

        patron = patronService.find("patron1");
        assertEquals(patron.getTelephoneNumber(), "8018640850");

    }

}
