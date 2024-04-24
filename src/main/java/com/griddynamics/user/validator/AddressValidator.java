package com.griddynamics.user.validator;

import com.griddynamics.user.dtos.AddressDto;

public class AddressValidator {

    private boolean validateAddress(String address) {
        return address != null && !address.isEmpty();
    }

    private boolean validateCity(String city) {
        return city != null && !city.isEmpty();
    }

    private boolean validateCountry(String country) {
        return country != null && !country.isEmpty();
    }

    private boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?(-?\\s?[0-9])+$");
    }

    private boolean validateStateProvinceRegion(String stateProvinceRegion) {
        return stateProvinceRegion != null && !stateProvinceRegion.isEmpty();
    }

    private boolean validateStreetAddress(String streetAddress) {
        return streetAddress != null && !streetAddress.isEmpty();
    }

    private boolean validateStreetAddress2(String streetAddress2) {
        return streetAddress2 != null && !streetAddress2.isEmpty();
    }

    private boolean validateZipCode(String zipCode) {
        return zipCode.matches("^\\d{5}$");
    }

    public boolean validateAddress(AddressDto addressDto) {
        return validateAddress(addressDto.getStreetAddress()) &&
                validateCity(addressDto.getCity()) &&
                validateCountry(addressDto.getCountry()) &&
                validateName(addressDto.getName()) &&
                validatePhoneNumber(addressDto.getPhoneNumber()) &&
                validateStateProvinceRegion(addressDto.getStateProvinceRegion()) &&
                validateStreetAddress(addressDto.getStreetAddress()) &&
                validateStreetAddress2(addressDto.getStreetAddress2()) &&
                validateZipCode(addressDto.getZipCode());
    }


}
