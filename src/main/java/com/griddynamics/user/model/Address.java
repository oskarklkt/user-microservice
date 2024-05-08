package com.griddynamics.user.model;

import com.google.gson.GsonBuilder;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
