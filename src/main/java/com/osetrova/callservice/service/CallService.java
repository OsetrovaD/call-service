package com.osetrova.callservice.service;

import com.osetrova.callservice.dto.FullCallInfo;
import com.osetrova.callservice.exception.CallServiceException;
import com.osetrova.callservice.util.PhoneNumberUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

@Service
public class CallService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String UNDERLINE = "_";
    private static final String EXTENSION = ".txt";
    private static final String SPACE = " ";

    public void saveCall(FullCallInfo callInfo) {
        Path path = Paths.get("calls", getFileName(callInfo.getFirstName(), callInfo.getLastName()));
        File file = path.toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(callInfo.getTime().format(FORMATTER));
            writer.append(SPACE);
            writer.append(PhoneNumberUtil.formatForWriting(callInfo.getNumber()));
            writer.newLine();
        } catch (IOException e) {
            throw new CallServiceException("Service couldn't accept the call.");
        }
    }

    private String getFileName(String firstName, String lastName) {
        String result;

        if (!firstName.isEmpty()) {
            result = lastName.toUpperCase() + UNDERLINE + firstName.toUpperCase() + EXTENSION;
        } else {
            result = lastName.toUpperCase() + EXTENSION;
        }

        return result;
    }
}
