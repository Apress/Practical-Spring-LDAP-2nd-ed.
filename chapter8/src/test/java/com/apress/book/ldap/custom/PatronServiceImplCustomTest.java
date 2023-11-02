package com.apress.book.ldap.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test-converter.xml")
@DisplayName("Patron Service Custom test cases")
class PatronServiceImplCustomTest {

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
        patron.setPhoneNumber(new PhoneNumber(801, 864, 8050));

        patronService.create(patron);

        // Lets read the patron
        Patron result = patronService.find("patron10001");
        assertAll(() -> assertNotNull(result), () -> assertEquals(patron.getDn().toString(), result.getDn().toString()),
                () -> assertEquals(801, patron.getPhoneNumber().getAreaCode()),
                () -> assertEquals(864, patron.getPhoneNumber().getExchange()),
                () -> assertEquals(8050, patron.getPhoneNumber().getExtension()));
    }

    @Test
    @DisplayName("Check that operation search return a patron")
    void should_find_a_patron() {
        Patron result = patronService.find("patron100");
        assertAll(() -> assertNotNull(result),
                () -> assertEquals("uid=patron100,ou=patrons,dc=inflinx,dc=com", result.getDn().toString()),
                () -> assertEquals(232, result.getPhoneNumber().getAreaCode()),
                () -> assertEquals(25, result.getPhoneNumber().getExchange()),
                () -> assertEquals(4008, result.getPhoneNumber().getExtension()));
    }

    @Test
    @DisplayName("Check that operation update an entry on the LDAP")
    void should_update_a_patron() {
        Patron patron = patronService.find("patron1");
        assertNotNull(patron);

        patron.setPhoneNumber(new PhoneNumber(1, 2, 3));
        patronService.update(patron);

        Patron result = patronService.find("patron1");
        assertAll(() -> assertNotNull(result),
                () -> assertEquals("uid=patron1,ou=patrons,dc=inflinx,dc=com", result.getDn().toString()),
                () -> assertEquals(1, result.getPhoneNumber().getAreaCode()),
                () -> assertEquals(2, result.getPhoneNumber().getExchange()),
                () -> assertEquals(3, result.getPhoneNumber().getExtension()));
    }
}