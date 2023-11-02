#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package {package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

@Repository("ldapExample")
public class LdapExample {

    @Autowired
    @Qualifier("ldapTemplate")
    private LdapTemplate ldapTemplate;

}
