package com.osetrova.callservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FullCallInfo {

    private String number;
    private String firstName;
    private String lastName;
    private LocalTime time;
}
