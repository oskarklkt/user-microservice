package com.griddynamics.user.mapper.modelToDto;

import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientDiscountInfoDtoMapper implements Function<User, ClientDiscountInfoDto> {

    @Override
    public ClientDiscountInfoDto apply(User user) {
        return  ClientDiscountInfoDto.builder()
                .clientType(user.getClientType())
                .dateOfAccountCreation(user.getDateOfAccountCreation())
                .build();
    }
}
