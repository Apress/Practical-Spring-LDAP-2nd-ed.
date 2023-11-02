package com.apress.book.ldap.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("Authentication Dao 2 test cases")
class AuthenticationDao2Test {

    @Autowired
    @Qualifier("authenticationDao2")
    private AuthenticationDao authenticationDao;

    @Test
    @DisplayName("Check is the authentication mechanism works fine")
    void should_authentication_works() {
        boolean authResult = authenticationDao.authenticate("patron0", "password");
        assertTrue(authResult);

        authResult = authenticationDao.authenticate("patron0", "invalidPassword");
        assertFalse(authResult);
    }
}