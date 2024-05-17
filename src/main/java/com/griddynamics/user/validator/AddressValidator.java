package com.griddynamics.user.validator;

import com.griddynamics.user.dto.AddressDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator {
    private static final String PHONE_NUMBER_REGEX = "^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?(-?\\s?[0-9])+$";
    private static final String ZIP_CODE_REGEX = "^\\d{5}$";

    private boolean validateAddress(String address) {
        return !StringUtils.isEmpty(address) && !address.isEmpty();
    }

    private boolean validateCity(String city) {
        return !StringUtils.isEmpty(city);
    }

    private boolean validateCountry(String country) {
        return !StringUtils.isEmpty(country);
    }

    private boolean validateName(String name) {
        return !StringUtils.isEmpty(name);
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return !StringUtils.isEmpty(phoneNumber) &&
                phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    private boolean validateStateProvinceRegion(String stateProvinceRegion) {
        return !StringUtils.isEmpty(stateProvinceRegion);
    }

    private boolean validateStreetAddress(String streetAddress) {
        return !StringUtils.isEmpty(streetAddress);
    }

    private boolean validateStreetAddress2(String streetAddress2) {
        return !StringUtils.isEmpty(streetAddress2);
    }

    private boolean validateZipCode(String zipCode) {
        return zipCode.matches(ZIP_CODE_REGEX);
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
