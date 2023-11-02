package com.apress.book.ldap.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Calculator tests cases")
class CalculatorTest {

    private MathService mathService;

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        mathService = mock(MathService.class); // This class is a mock
        calculator = new Calculator(mathService);
    }

    @Test
    @DisplayName("Check that the sum of two numbers is okay")
    void should_sum_two_numbers() {
        // Given - Creating the mocks
        when(mathService.add(1, 4)).thenReturn(5);

        // When - Execution of the operation
        int result = calculator.add(1, 4);

        // Then - Verify the results and the interactions with the mocks class
        verify(mathService).add(1, 4);
        assertEquals(5, result);
    }
}
