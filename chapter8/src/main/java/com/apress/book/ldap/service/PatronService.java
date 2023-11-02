package com.apress.book.ldap.service;

import com.apress.book.ldap.domain.Patron;

public interface PatronService {

    void create(Patron patron);

    void delete(String id);

    void update(Patron patron);

    Patron find(String id);
}
