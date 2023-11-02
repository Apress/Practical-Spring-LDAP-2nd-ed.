package com.apress.book.ldap.filter;

import com.apress.book.ldap.domain.Patron;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
@DisplayName("Search Filter test cases")
class SearchFilterTest {

    @Autowired
    private SearchFilter searchFilter;

    @Test
    @DisplayName("Check that search operation using equals filter works")
    void should_works_equalsFilter() {
        Filter filter = new EqualsFilter("givenName", "Abbi");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(1, results.size()),
                () -> assertEquals("Abbi Abbott", results.get(0).getFullName()));
    }

    @Test
    @DisplayName("Check that search operation using like filter works")
    void should_works_likeFilter() {
        Filter filter = new LikeFilter("givenName", "Abb*");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(7, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using present filter works")
    void should_works_presentFilter() {
        Filter filter = new PresentFilter("mail");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(100, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using non present filter works")
    void should_works_notPresentFilter() {
        Filter filter = new NotPresentFilter("mail");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(2, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using non filter works")
    void should_works_notFilter() {
        NotFilter notFilter = new NotFilter(new LikeFilter("givenName", "Ade*"));
        List<Patron> results = searchFilter.searchAndPrintResults(notFilter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(86, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using greater than or equals filter works")
    void should_works_greaterThanOrEqualsFilter() {
        Filter filter = new GreaterThanOrEqualsFilter("postalCode", "57018");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(46, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using less than or equals filter works")
    void should_works_lessThanOrEqualsFilter() {
        Filter filter = new LessThanOrEqualsFilter("postalCode", "57018");
        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(56, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using and filter works")
    void should_works_andFilter() {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("postalCode", "95571"));
        andFilter.and(new PresentFilter("mail"));
        List<Patron> results = searchFilter.searchAndPrintResults(andFilter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(1, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using or filter works")
    void should_works_orFilter() {
        OrFilter orFilter = new OrFilter();
        orFilter.or(new EqualsFilter("postalCode", "51911"));
        orFilter.or(new EqualsFilter("postalCode", "48200"));
        List<Patron> results = searchFilter.searchAndPrintResults(orFilter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(2, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using hardcoded filter works")
    void should_works_HardcodedFilter() {
        String searchExpression = "(sn=Aguirre)";
        AndFilter filter = new AndFilter();
        filter.and(new HardcodedFilter(searchExpression));
        filter.and(new EqualsFilter("givenName", "Aggie"));

        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(1, results.size()));
    }

    @Test
    @DisplayName("Check that search operation using whitespace wildcard filter works")
    void should_works_WhitespaceWildcardsFilter() {
        WhitespaceWildcardsFilter filter = new WhitespaceWildcardsFilter("cn", "Abbey Abbie");

        List<Patron> results = searchFilter.searchAndPrintResults(filter);
        assertAll(() -> assertNotNull(results), () -> assertEquals(1, results.size()));
    }
}