package com.osetrova.callservice.validator;

import com.osetrova.callservice.dto.CallInfo;
import com.osetrova.callservice.util.PhoneNumberUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements BaseValidator<CallInfo> {

    private static final String BRACKETS = "()";
    private static final String REGEX = "^((\\+|0( ?-?|-? ?)0)(( ?-?|-? ?)\\d){3})?(( ?-?|-? ?)\\d){9}";

    @Override
    public void validateObject(CallInfo callInfo, Errors errors) {
        if (callInfo.getLastName().isEmpty()) {
            errors.reject("101", "Field \"Last name\" cannot be empty.");
        } else if (callInfo.getNumber().isEmpty()) {
            errors.reject("101", "Field \"Number\" cannot be empty.");
        } else if (!isNumberValid(callInfo.getNumber())) {
            errors.reject("101", "Number is invalid.");
        }
    }

    private boolean isNumberValid(String number) {
        boolean result = false;

        if (areBracketsValid(number)) {
            String numberWithoutBrackets = PhoneNumberUtil.removeBrackets(number);

            result = Pattern.matches(REGEX, numberWithoutBrackets);
        }

        return result;
    }

    private boolean areBracketsValid(String number) {
        int leftBracketAmount = 0;
        int rightBracketAmount = 0;
        int leftBracketIndexesSum = 0;
        int rightBracketIndexesSum = 0;

        for (int i = 0; i < number.length(); i++) {
            if (BRACKETS.charAt(0) == number.charAt(i)) {
                leftBracketAmount++;
                leftBracketIndexesSum += i;
            } else if (BRACKETS.charAt(1) == number.charAt(i)) {
                rightBracketAmount++;
                rightBracketIndexesSum += i;
            }
        }

        return leftBracketAmount == 0 && rightBracketAmount == 0
                || leftBracketAmount == rightBracketAmount && rightBracketIndexesSum - leftBracketIndexesSum > 0;
    }
}
