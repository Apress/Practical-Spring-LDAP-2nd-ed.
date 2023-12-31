package com.apress.book.ldap.control;

import javax.naming.ldap.Control;

import org.springframework.ldap.control.AbstractFallbackRequestAndResponseControlDirContextProcessor;

public class SortMultipleControlDirContextProcessor
        extends AbstractFallbackRequestAndResponseControlDirContextProcessor {

    // The keys to sort on
    private String[] sortKeys;

    // Did the results actually get sorted?
    private boolean sorted;

    // The result code of the operation
    private int resultCode;

    public SortMultipleControlDirContextProcessor(String... sortKeys) {
        if (sortKeys.length == 0) {
            throw new IllegalArgumentException("You must provide lease one key to sort on");
        }

        this.sortKeys = sortKeys;
        this.sorted = false;
        this.resultCode = -1;

        this.defaultRequestControl = "javax.naming.ldap.SortControl";
        this.defaultResponseControl = "javax.naming.ldap.SortResponseControl";
        this.fallbackRequestControl = "com.sun.jndi.ldap.ctl.SortControl";
        this.fallbackResponseControl = "com.sun.jndi.ldap.ctl.SortResponseControl";

        loadControlClasses();
    }

    @Override
    public Control createRequestControl() {
        return super.createRequestControl(new Class[] { String[].class, boolean.class },
                new Object[] { sortKeys, critical });
    }

    @Override
    protected void handleResponse(Object control) {
        this.sorted = (Boolean) invokeMethod("isSorted", responseControlClass, control);
        this.resultCode = (Integer) invokeMethod("getResultCode", responseControlClass, control);
    }

    public String[] getSortKeys() {
        return sortKeys;
    }

    public boolean isSorted() {
        return sorted;
    }

    public int getResultCode() {
        return resultCode;
    }
}