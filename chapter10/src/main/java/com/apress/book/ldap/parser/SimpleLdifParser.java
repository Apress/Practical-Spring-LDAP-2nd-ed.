package com.apress.book.ldap.parser;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ldap.core.LdapAttributes;
import org.springframework.ldap.ldif.parser.LdifParser;

public class SimpleLdifParser {

    private static final Logger logger = LoggerFactory.getLogger(SimpleLdifParser.class);

    public void parse(File file) throws IOException {
        LdifParser parser = new LdifParser(file);
        parser.open();
        int count = 0;
        while (parser.hasMoreRecords()) {
            LdapAttributes attributes = parser.getRecord();
            count++;
        }
        parser.close();
        logger.info(String.valueOf(count));
    }

    public static void main(String[] args) throws IOException {
        SimpleLdifParser parser = new SimpleLdifParser();
        parser.parse(new ClassPathResource("patrons.ldif").getFile());
    }
}