package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface AddressDtoMapper {
    AddressDtoMapper INSTANCE = Mappers.getMapper(AddressDtoMapper.class);

    @Mapping(source = "userId", target = "name", qualifiedByName = "extractName")
    @Mapping(source = "userId", target = "surname", qualifiedByName = "extractSurname")

    @Named("extractName")
    default String extractName(Address address) {
        return UserRepository.getUsers().get(address.getUserId()).getName();
    }

    @Named("extractSurname")
    default String extractSurname(Address address, Optional<User> user) {
        return user.map(User::getSurname).orElse(null);
    }

    AddressDto addressToAddressDto(Address address, Optional<User> user);

    Address addressDtoToAddress(AddressDto addressDto);



    @AfterMapping
    default void setAddressId(@MappingTarget Address address) {
        address.setId(AddressRepository.getNextAddressId());
    }
}
