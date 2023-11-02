package com.apress.book.ldap.mocks;

public class Calculator {

    private final MathService service;

    public Calculator(MathService service) {
        this.service = service;
    }

    public int add(int numberOne, int numberTwo) {
        return service.add(numberOne, numberTwo);
    }
}
