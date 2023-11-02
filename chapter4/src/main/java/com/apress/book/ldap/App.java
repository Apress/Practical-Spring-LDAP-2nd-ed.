package com.apress.book.ldap;

import com.apress.book.ldap.client.LdapOperationsClient;
import com.apress.book.ldap.domain.Patron;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        LdapOperationsClient client = context.getBean(LdapOperationsClient.class);

        client.updateEmail("patron100", "patron100fake@inflinx.com");

        List<Patron> patrons = client.search();
        for (Patron patron : patrons) {
            System.out.println(patron);
        }
    }
}
