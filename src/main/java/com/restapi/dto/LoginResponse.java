package com.restapi.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {
    private final String accessToken;
}
