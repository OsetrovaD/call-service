package com.osetrova.callservice.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class PhoneNumberUtil {

    private static final String BRACKETS = "[()]";
    private static final String EMPTY_STRING = "";
    private static final String ALL_EXCEPT_DIGITS_AND_PLUS = "[\\D&&[^+]]";
    private static final String NUMBER_FORMAT_FOR_WRITING = "%d%d%d%d%d %d%d%d %d%d%d %d%d%d";
    private static final String REGEX = "%d";
    private static final String DEFAULT_CODE = "00420";
    private static final String DEFAULT_PREFIX = "00";

    public static String removeBrackets(String number) {
        return number.replaceAll(BRACKETS, EMPTY_STRING);
    }

    public static String formatForWriting(String number) {
        String result = number.replaceAll(ALL_EXCEPT_DIGITS_AND_PLUS, EMPTY_STRING);
        if (result.length() != 9) {
            result = result.replaceFirst("\\+", DEFAULT_PREFIX);
        } else {
            result = DEFAULT_CODE + result;
        }

        return formatByRegex(result.toCharArray());
    }

    private String formatByRegex(char[] digits) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(NUMBER_FORMAT_FOR_WRITING);
        StringBuffer stringBuffer = new StringBuffer();
        int digitIndex = 0;
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, String.valueOf(digits[digitIndex++]));
        }
        matcher.appendTail(stringBuffer);

        return stringBuffer.toString();
    }
}
