package com.griddynamics.user.dtos;
import com.griddynamics.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {
    private String name;
    private String surname;
    private Gender gender;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String profilePhotoUrl;
}
