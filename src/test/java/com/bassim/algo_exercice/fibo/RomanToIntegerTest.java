package com.bassim.algo_exercice.fibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RomanToIntegerTest {

    @Test
    public void testSimpleValues() {
        assertEquals(3, RomanToInteger.romanToInt("III"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII")); // 50 + 5 + 3
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
    }

    @Test
    public void testSubtractiveForms() {
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(40, RomanToInteger.romanToInt("XL"));
        assertEquals(90, RomanToInteger.romanToInt("XC"));
        assertEquals(400, RomanToInteger.romanToInt("CD"));
        assertEquals(900, RomanToInteger.romanToInt("CM"));
    }

    @Test
    public void testLargeNumbers() {
        assertEquals(3888, RomanToInteger.romanToInt("MMMDCCCLXXXVIII")); // 3000+500+300+80+8
        assertEquals(2763, RomanToInteger.romanToInt("MMDCCLXIII"));
    }
}
