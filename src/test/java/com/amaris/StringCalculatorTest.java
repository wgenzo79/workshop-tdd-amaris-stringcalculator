package com.amaris;

import com.amaris.exception.NegativesNotAllowedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        int sum = stringCalculator.add("\\;\n7;2");
        assertEquals(9, sum);
    }

    @Test
    public void withMoreThanOneCharDelimitator() throws Exception {
        int sum = stringCalculator.add("\\:;-\n7:;-2");
        assertEquals(9, sum);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenNegativesThenException() throws Exception {
        exception.expect(NegativesNotAllowedException.class);
        exception.expectMessage("Negatives not allowed => [-1]");
        stringCalculator.add("2, -1");
    }

    @Test
    public void whenMoreThanOneNegativesThenExceptionPrintsAll() throws Exception {
        exception.expect(NegativesNotAllowedException.class);
        exception.expectMessage("Negatives not allowed => [-1, -10]");
        stringCalculator.add("2, -1, 2, -10");
    }
}
