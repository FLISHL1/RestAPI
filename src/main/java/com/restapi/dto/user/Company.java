package com.restapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
