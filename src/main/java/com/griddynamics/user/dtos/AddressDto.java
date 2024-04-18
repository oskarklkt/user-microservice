package com.griddynamics.user.dtos;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class AddressDto {
    private final Long id;
    private final String country;
    private final String name;
    private final String surname;
    private final String streetAddress;
    private final String streetAddress2;
    private final String city;
    private final String stateProvinceRegion;
    private final String zipCode;
    private final String phoneNumber;
}
