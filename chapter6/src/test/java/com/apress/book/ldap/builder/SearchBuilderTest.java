package com.apress.book.ldap.builder;

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
@DisplayName("Search Builder test cases")
class SearchBuilderTest {

    @Autowired
    private SearchBuilder searchBuilder;

    @Test
    @DisplayName("Check that search operation using lastname and postal code works")
    void should_works_builder() {
        List<Patron> results = searchBuilder.getPatronByLastNameAndPostalCode("Abbott", "07007");
        assertAll(() -> assertNotNull(results), () -> assertEquals(1, results.size()),
                () -> assertEquals("Abbi Abbott", results.get(0).getFullName()));
    }
}