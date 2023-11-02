package com.apress.book.ldap.sorting;

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
@DisplayName("Spring Sorting uses cases")
class SpringSortingTest {

    @Autowired
    private SpringSorting springSorting;

    @Test
    @DisplayName("Sorting by lastname")
    void should_sort_byLastName() {
        List<String> results = springSorting.sortByLastName();
        assertAll(() -> assertNotNull(results), () -> assertEquals(101, results.size()),
                () -> assertEquals("Aalders", results.get(0)));
    }

    @Test
    @DisplayName("Sorting by location")
    void should_sort_by_location() {
        List<String> results = springSorting.sortByLocation();
        assertAll(() -> assertNotNull(results), () -> assertEquals(101, results.size()),
                () -> assertEquals("AK,Abilene", results.get(0)));
    }
}