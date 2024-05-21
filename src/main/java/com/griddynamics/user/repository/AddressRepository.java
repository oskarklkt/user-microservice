package com.griddynamics.user.repository;
import com.griddynamics.user.datasource.QueryHandler;
import com.griddynamics.user.mapper.resultsetToModel.ResultSetAddressMapper;
import com.griddynamics.user.model.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
//TODO change to @Repository when we will start Spring data JPA
@Component
@AllArgsConstructor
public class AddressRepository {

    private final QueryHandler<Address> addressQueryHandler;
    private final ResultSetAddressMapper resultSetAddressMapper;

    private final static String SAVE_ADDRESS_QUERY = "INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String DELETE_ADDRESS_QUERY = "DELETE FROM addresses WHERE user_id = ? AND id = ?";
    private final static String FIND_ALL_BY_USER_ID_QUERY = "SELECT * FROM addresses WHERE user_id = ?";
    private final static String SELECT_ALL_ADDRESSES_QUERY = "SELECT * FROM addresses";


    public void save(Long userId, Address address) {
        addressQueryHandler.execute(SAVE_ADDRESS_QUERY, userId, address.getCountry(), address.getStreetAddress(), address.getStreetAddress2(),
                address.getCity(), address.getStateProvinceRegion(), address.getZipCode(), address.getPhoneNumber());
    }

    public void deleteAddress(Long userId, Long addressId) {
        addressQueryHandler.execute(DELETE_ADDRESS_QUERY, userId, addressId);
    }

    public List<Address> findAllByUserId(Long userId) {
        return addressQueryHandler.findMany(FIND_ALL_BY_USER_ID_QUERY, resultSetAddressMapper, userId);
    }

    public Long getNextAddressId() {
        return (long) addressQueryHandler.findMany(SELECT_ALL_ADDRESSES_QUERY, resultSetAddressMapper).size();
    }
}