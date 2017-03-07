package com.amaris;

import com.amaris.exception.NegativesNotAllowedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void whenEmptyThenZero() throws Exception {
        int sum = stringCalculator.add("");
        assertEquals(0, sum);
    }

    @Test
    public void whenOneNumberThenSameResult() throws Exception {
        int sum = stringCalculator.add("21");
        assertEquals(21, sum);
    }

    @Test
    public void whenTwoNumbersThenSum() throws Exception {
        int sum = stringCalculator.add("4, 8");
        assertEquals(12, sum);
    }

    @Test
    public void whenFiveNumbersThenSum() throws Exception {
        int sum = stringCalculator.add("2, 2, 2, 2, 2");
        assertEquals(10, sum);
    }

    @Test
    public void withNewLineSeparator() throws Exception {
        int sum = stringCalculator.add("1\n2,3");
        assertEquals(6, sum);
    }

    @Test
    public void withNewDelimitator() throws Exception {
        int sum = stringCalculator.add(";\n7;2");
        assertEquals(9, sum);
    }

    @Test(expected = NegativesNotAllowedException.class)
    public void whenNegativesThenException() throws Exception {
        try {
            stringCalculator.add("2, -1");
        } catch (Exception e) {
            System.out.println("StringCalculatorTest.whenNegativesThenException - " + e.getLocalizedMessage());
            throw e;
        }
    }

    @Test
    public void whenMoreThanOneNegativesThenExceptionPrintsAll() throws Exception {
        try {
            stringCalculator.add("2, -1, 2, -10");
        } catch (NegativesNotAllowedException e) {
            System.out.println("StringCalculatorTest.whenMoreThanOneNegativesThenExceptionPrintsAll - " + e.getLocalizedMessage());
            assertEquals("Negatives not allowed => [-1, -10]", e.getLocalizedMessage());
        }
    }
}
