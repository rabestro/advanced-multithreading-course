package com.epam.engx.jam.printer;

import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

@Component
public class BigIntegerPrinter implements Printer<BigInteger> {
    private static final int MAX_DIGITS = 30;

    @Override
    public String print(BigInteger number, Locale locale) {
        if (number.toString().length() > MAX_DIGITS) {
            return "%-30.15e".formatted(new BigDecimal(number));
        }
        return "%,d".formatted(number);
    }

    public String print(BigInteger number) {
        return print(number, Locale.US);
    }
}
