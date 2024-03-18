package com.restapi.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(Address address) {
        this.address = address;
    }

    @JsonProperty("address")
    private void unpackNestedAddress(Map<String, Object> address) {
        this.address = Address.builder()
                .street((String) address.get("street"))
                .suite((String) address.get("suite"))
                .city((String) address.get("city"))
                .zipcode((String) address.get("zipcode"))
                .geo(Geo.builder()
                        .lat(((Map<String, String>) address.get("geo")).get("lat"))
                        .lng(((Map<String, String>) address.get("geo")).get("lng"))
                        .build())
                .build();
    }

    @JsonProperty("company")
    private void unpackNestedCompany(Map<String, Object> company) {
        this.company = Company.builder()
                .name((String) company.get("name"))
                .catchPhrase((String) company.get("catchPhrase"))
                .bs((String) company.get("bs"))
                .build();
    }
}
