package com.apress.book.ldap.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("String Utils tests cases")
class StringUtilsTest {
    @Test
    @DisplayName("String Utils should return true when the input is null")
    void should_return_true_with_null() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    @DisplayName("String Utils should return true when the input is empty")
    void should_return_true_with_empty() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    @DisplayName("String Utils should return false when the input have a value")
    void should_return_false_with_text() {
        assertFalse(StringUtils.isEmpty("Practical Spring Ldap"));
    }
}
