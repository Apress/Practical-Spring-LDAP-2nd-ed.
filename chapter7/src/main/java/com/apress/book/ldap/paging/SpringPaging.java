package com.apress.book.ldap.paging;

import java.util.List;

import javax.naming.directory.SearchControls;

import com.apress.book.ldap.domain.Page;
import com.apress.book.ldap.domain.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

@Component("springPaging")
public class SpringPaging {

    private static final Logger logger = LoggerFactory.getLogger(SpringPaging.class);

    private LdapTemplate ldapTemplate;

    public SpringPaging(@Autowired @Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void pagedResults() {

        PagedResultsCookie cookie = null;

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        int page = 1;
        do {
            logger.info("Starting Page: " + page);
            PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(20, cookie);
            EqualsFilter equalsFilter = new EqualsFilter("objectClass", "inetOrgPerson");

            List<String> lastNames = ldapTemplate.search("", equalsFilter.encode(), searchControls,
                    new LastNameMapper(), processor);

            for (String l : lastNames) {
                logger.info(l);
            }

            cookie = processor.getCookie();
            page = page + 1;

        } while (null != cookie.getCookie());
    }

    public Pagination pagedResults(Page page) {

        int pageNumber = 0;
        PagedResultsCookie cookie = null;

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        if (page.getCookie() != null) {
            pageNumber = page.getActualPage();
            cookie = page.getCookie();
        }

        logger.info("Page: " + page);
        PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(20, cookie);
        EqualsFilter equalsFilter = new EqualsFilter("objectClass", "inetOrgPerson");

        List<String> lastNames = ldapTemplate.search("", equalsFilter.encode(), searchControls, new LastNameMapper(),
                processor);

        for (String l : lastNames) {
            logger.info(l);
        }

        return new Pagination(lastNames,
                new Page(processor.getPageSize(), processor.getResultSize(), pageNumber + 1, processor.getCookie()));
    }

    private class LastNameMapper extends AbstractContextMapper<String> {
        @Override
        protected String doMapFromContext(DirContextOperations context) {
            return context.getStringAttribute("sn");
        }
    }

}
