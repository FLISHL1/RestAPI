package com.restapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
class Geo {
    private String lat;
    private String lng;
}
