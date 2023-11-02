package com.apress.book.ldap.transactions;

import java.util.List;

import com.apress.book.ldap.domain.Patron;

public interface PatronService {

    void create(Patron patron);

    void create(List<Patron> patronList);

    void delete(String id);

    void update(Patron patron);

    Patron find(String id);
}
