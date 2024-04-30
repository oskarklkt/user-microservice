package com.griddynamics.user.model;

import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.enumeration.ClientType;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private Gender gender;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String profilePhotoUrl;
    private String dateOfAccountCreation;
    private ClientType clientType;
}
