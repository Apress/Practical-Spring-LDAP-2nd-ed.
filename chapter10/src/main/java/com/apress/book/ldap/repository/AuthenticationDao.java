package com.apress.book.ldap.repository;

public interface AuthenticationDao {
    boolean authenticate(String userid, String password);
}
