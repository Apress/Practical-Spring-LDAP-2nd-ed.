package com.apress.book.ldap.repository;

import com.apress.book.ldap.domain.Patron;

public interface PatronDao {
    void create(Patron patron);

    void delete(String id);

    void update(Patron patron);

    Patron find(String id);
}
