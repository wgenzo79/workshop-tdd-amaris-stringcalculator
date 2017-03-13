package com.amaris;

import com.amaris.exception.NegativesNotAllowedException;

import java.util.ArrayList;

public class StringCalculator {

    public static final String DEFAULT_DELIMITERS = ",|\n";
    public static final String NEW_LINE = "\n";

    public int add(String str) throws NegativesNotAllowedException {
        int sum = 0;

        if(!str.isEmpty())
            sum = makeAddition(getNumbers(str));

        return sum;
    }

    private String[] getNumbers(String str) {
        if(str.startsWith("\\"))
            return getNumbersWithNewDelimiter(str);

        return str.split(DEFAULT_DELIMITERS);
    }

    private String[] getNumbersWithNewDelimiter(String str) {
        String[] newStr = str.split(NEW_LINE, 2);
        return newStr[1].split(newStr[0].substring(1));
    }

    private int makeAddition(final String[] numbers) throws NegativesNotAllowedException {
        int result = 0;
        ArrayList<Integer> negatives = new ArrayList<>();

        for (String number : numbers) {
            int num = Integer.parseInt(number.trim());
            if (num < 0) negatives.add(num);
            result += num;
        }

        if (!negatives.isEmpty())
            throw new NegativesNotAllowedException("Negatives not allowed => " + negatives);

        return result;
    }
}
