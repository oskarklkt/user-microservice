package com.griddynamics.user.validator;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.repository.UserRepository;

public class UserValidator {
    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$");
    }

    private boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    private boolean isSurnameValid(String surname) {
        return surname.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    private boolean isBirthdayValid(String birthday) {
        return birthday.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    private boolean isGenderValid(String gender) {
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female");
    }

    public boolean isUserInDatabase(Long userId) {
        return UserRepository.getUsers().containsKey(userId);
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
