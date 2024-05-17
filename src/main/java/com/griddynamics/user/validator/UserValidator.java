package com.griddynamics.user.validator;

import com.griddynamics.user.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";
    private static final String NAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private static final String SURNAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private static final String BIRTHDAY_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";


    private boolean isEmailValid(String email) {
        return !StringUtils.isEmpty(email) && email.matches(EMAIL_REGEX);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return !StringUtils.isEmpty(phoneNumber) && phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    private boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    private boolean isSurnameValid(String surname) {
        return !StringUtils.isEmpty(surname) && surname.matches(SURNAME_REGEX);
    }

    private boolean isBirthdayValid(String birthday) {
        return !StringUtils.isEmpty(birthday) && birthday.matches(BIRTHDAY_REGEX);
    }

    private boolean isGenderValid(String gender) {
        return !StringUtils.isEmpty(gender) && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
    }

    public boolean isUserDtoValid(UserDto userDto) {
        return isEmailValid(userDto.getEmail()) &&
                isPhoneNumberValid(userDto.getPhoneNumber()) &&
                isNameValid(userDto.getName()) &&
                isSurnameValid(userDto.getSurname()) &&
                isBirthdayValid(userDto.getBirthday()) &&
                isGenderValid(userDto.getGender().toString());
    }


}
