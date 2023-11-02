package com.apress.book.ldap.paging;

import com.apress.book.ldap.domain.Page;
import com.apress.book.ldap.domain.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("Spring Paging uses cases")
class SpringPagingTest {

    @Autowired
    private SpringPaging springPaging;

    @Test
    @DisplayName("Check if all the results are paginated")
    void should_paginate_all_results() {
        springPaging.pagedResults();
    }

    @Test
    @DisplayName("Check the results of the first page")
    void should_paginate_first_pade() {
        Pagination result = springPaging.pagedResults(new Page());

        assertAll(() -> assertNotNull(result), () -> assertEquals(1, result.getPage().getActualPage()),
                () -> assertEquals(20, result.getPage().getPageSize()),
                () -> assertEquals(101, result.getPage().getNumberElements()),
                () -> assertEquals("Amar", result.getResult().get(0)));
    }
}