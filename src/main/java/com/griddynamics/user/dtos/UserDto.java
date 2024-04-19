package com.griddynamics.user.dtos;

import com.google.gson.GsonBuilder;
import com.griddynamics.user.enums.Gender;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private Gender gender;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String profilePhotoUrl;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}


