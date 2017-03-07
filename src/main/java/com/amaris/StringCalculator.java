package com.amaris;

import com.amaris.exception.NegativesNotAllowedException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final String DEFAULT_DELIMITERS = ",|\n";
    public static final String NEW_DELIMITER_REGEX = "[^0-9]\n.*";
    public static final String NEW_LINE = "\n";

    public int add(String str) throws NegativesNotAllowedException {
        int sum = 0;

        if(!str.isEmpty()) {
            String delimitersRegEx = DEFAULT_DELIMITERS;

            if(hasNewDelimiter(str)) {
                String[] newStr = str.split(NEW_LINE, 2);
                delimitersRegEx = newStr[0];
                str = newStr[1];
            }

            sum = makeAddition(str.split(delimitersRegEx));
        }

        return sum;
    }

    private boolean hasNewDelimiter(String str) {
        return Pattern.matches(NEW_DELIMITER_REGEX, str);
    }

    private int makeAddition(final String[] numbers) throws NegativesNotAllowedException {
        int result = 0;
        ArrayList<Integer> negatives = new ArrayList<>();

        for (String number : numbers) {
            int num = Integer.parseInt(number.trim());
            if (num < 0) negatives.add(num);
            else result += num;
        }

        if (!negatives.isEmpty())
            throw new NegativesNotAllowedException("Negatives not allowed => " + negatives);

        return result;
    }
}
