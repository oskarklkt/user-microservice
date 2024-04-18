package com.griddynamics.user.models;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {
    @Setter
    private Long id;
    private final Long userId;
    private final String country;
    private final String streetAddress;
    private final String streetAddress2;
    private final String city;
    private final String stateProvinceRegion;
    private final String zipCode;
    private final String phoneNumber;
}
