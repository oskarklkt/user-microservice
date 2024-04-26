package com.griddynamics.user.model;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Builder
@NoArgsConstructor
public class Address {
    private Long id;
    private Long userId;
    private String country;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String stateProvinceRegion;
    private String zipCode;
    private String phoneNumber;
}
