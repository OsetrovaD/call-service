package com.osetrova.callservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CallInfo {

    private String number;
    private String firstName;
    private String lastName;
}
