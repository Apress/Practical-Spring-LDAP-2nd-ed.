package com.apress.book.ldap.exception;

import org.springframework.ldap.core.AuthenticationErrorCallback;

public class EmployeeAuthenticationErrorCallback implements AuthenticationErrorCallback {

    private Exception authenticationException;

    @Override
    public void execute(Exception ex) {
        this.authenticationException = ex;
    }

    public Exception getAuthenticationException() {
        return authenticationException;
    }

}