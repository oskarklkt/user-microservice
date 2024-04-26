package com.griddynamics.user.model;

import com.griddynamics.user.enumeration.Gender;
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
