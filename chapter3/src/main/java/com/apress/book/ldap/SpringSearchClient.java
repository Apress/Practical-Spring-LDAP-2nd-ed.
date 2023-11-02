package com.apress.book.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringSearchClient {

	private LdapTemplate ldapTemplate;
	@Autowired
	public SpringSearchClient(@Qualifier("ldapTemplate") LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<String> search() {
		
		return ldapTemplate.search("dc=inflinx,dc=com", "(objectclass=person)",
                (AttributesMapper) attributes -> (String)attributes.get("cn").get());
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SpringSearchClient client = context.getBean(SpringSearchClient.class);
		List<String> names = client.search();

		for(String name: names) {
			System.out.println(name);
		}
	}
}
