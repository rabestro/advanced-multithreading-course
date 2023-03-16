package com.epam.engx.jam.command;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class BigIntegerToStringConverter implements Converter<BigInteger, String> {
    private static final int MAX_DIGITS = 30;

    @Override
    public String convert(BigInteger source) {
        if (source.toString().length() > MAX_DIGITS) {
            return "%-30.15e".formatted(new BigDecimal(source));
        }
        return "%,d".formatted(source);
    }
}
