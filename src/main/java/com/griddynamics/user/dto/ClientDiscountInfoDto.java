package com.griddynamics.user.dto;

import com.google.gson.GsonBuilder;
import com.griddynamics.user.enumeration.ClientType;
import lombok.Builder;

@Builder
public class ClientDiscountInfoDto {
    private ClientType clientType;
    private String dateOfAccountCreation;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
