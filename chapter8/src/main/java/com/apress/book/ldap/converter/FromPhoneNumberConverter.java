package com.apress.book.ldap.converter;

import com.apress.book.ldap.custom.PhoneNumber;
import org.springframework.ldap.odm.typeconversion.impl.Converter;

public class FromPhoneNumberConverter implements Converter {

    @Override
    public <T> T convert(Object source, Class<T> toClass) throws Exception {
        T result = null;
        if (PhoneNumber.class.isAssignableFrom(source.getClass()) && toClass.equals(String.class)) {
            result = toClass.cast(source.toString());
        }
        return result;
    }
}
