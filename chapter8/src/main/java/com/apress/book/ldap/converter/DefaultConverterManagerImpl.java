package com.apress.book.ldap.converter;

import com.apress.book.ldap.custom.PhoneNumber;
import org.springframework.ldap.odm.typeconversion.ConverterManager;
import org.springframework.ldap.odm.typeconversion.impl.Converter;
import org.springframework.ldap.odm.typeconversion.impl.ConverterManagerImpl;
import org.springframework.ldap.odm.typeconversion.impl.converters.FromStringConverter;
import org.springframework.ldap.odm.typeconversion.impl.converters.ToStringConverter;

public class DefaultConverterManagerImpl implements ConverterManager {
    private static final Class[] classSet = { java.lang.Byte.class, java.lang.Integer.class, java.lang.Long.class,
            java.lang.Double.class, java.lang.Boolean.class };
    private ConverterManagerImpl converterManager;

    public DefaultConverterManagerImpl() {
        converterManager = new ConverterManagerImpl();
        Converter fromStringConverter = new FromStringConverter();
        Converter toStringConverter = new ToStringConverter();
        for (Class clazz : classSet) {
            converterManager.addConverter(String.class, null, clazz, fromStringConverter);
            converterManager.addConverter(clazz, null, String.class, toStringConverter);
        }

        Converter toPhoneNumberConverter = new ToPhoneNumberConverter();
        converterManager.addConverter(String.class, null, PhoneNumber.class, toPhoneNumberConverter);

        Converter fromPhoneNumberConverter = new FromPhoneNumberConverter();
        converterManager.addConverter(PhoneNumber.class, null, String.class, fromPhoneNumberConverter);
    }

    @Override
    public boolean canConvert(Class<?> fromClass, String syntax, Class<?> toClass) {
        return converterManager.canConvert(fromClass, syntax, toClass);
    }

    @Override
    public <T> T convert(Object source, String syntax, Class<T> toClass) {
        return converterManager.convert(source, syntax, toClass);
    }
}
