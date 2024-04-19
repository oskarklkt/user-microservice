package com.griddynamics.user.models;

import com.griddynamics.user.enums.Gender;
import lombok.*;


@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Setter
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private Gender gender;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String profilePhotoUrl;
}
