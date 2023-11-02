package com.apress.book.ldap.domain;

import org.springframework.ldap.control.PagedResultsCookie;

public class Page {

    int pageSize;
    int numberElements;

    int actualPage;

    PagedResultsCookie cookie;

    public Page() {

    }

    public Page(int pageSize, int numberElements, int actualPage, PagedResultsCookie cookie) {
        this.pageSize = pageSize;
        this.numberElements = numberElements;
        this.actualPage = actualPage;
        this.cookie = cookie;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNumberElements() {
        return numberElements;
    }

    public void setNumberElements(int numberElements) {
        this.numberElements = numberElements;
    }

    public int getActualPage() {
        return actualPage;
    }

    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }

    public PagedResultsCookie getCookie() {
        return cookie;
    }

    public void setCookie(PagedResultsCookie cookie) {
        this.cookie = cookie;
    }
}
