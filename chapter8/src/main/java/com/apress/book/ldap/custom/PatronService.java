package com.apress.book.ldap.custom;

public interface PatronService {

    void create(Patron patron);

    void delete(String id);

    void update(Patron patron);

    Patron find(String id);
}
