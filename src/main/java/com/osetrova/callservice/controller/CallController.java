package com.osetrova.callservice.controller;


import com.osetrova.callservice.dto.CallInfo;
import com.osetrova.callservice.dto.FullCallInfo;
import com.osetrova.callservice.service.CallService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalTime;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CallController {

    private CallService callService;

    @PostMapping(value = "/make-call", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void acceptCall(@Valid @RequestBody CallInfo callInfo) {
        callService.saveCall(FullCallInfo.builder()
                .firstName(callInfo.getFirstName())
                .lastName(callInfo.getLastName())
                .number(callInfo.getNumber())
                .time(LocalTime.now())
                .build());
    }
}
