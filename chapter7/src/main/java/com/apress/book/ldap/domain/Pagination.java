package com.apress.book.ldap.domain;

import java.util.List;

public class Pagination {
    List<String> result;

    Page page;

    public Pagination(List<String> result, Page page) {
        this.result = result;
        this.page = page;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
