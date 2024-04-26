package com.griddynamics.user.dto;


import com.google.gson.GsonBuilder;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Setter
@NoArgsConstructor
public class AddressDto {
    private Long userId;
    private String country;
    private String name;
    private String surname;
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
