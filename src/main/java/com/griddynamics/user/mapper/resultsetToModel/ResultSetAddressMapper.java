package com.griddynamics.user.mapper.resultsetToModel;

import com.griddynamics.user.model.Address;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.function.Function;

public class ResultSetAddressMapper implements Function<ResultSet, Address> {
    @Override
    @SneakyThrows
    public Address apply(ResultSet resultSet) {
        return new Address( resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("country"),
                            resultSet.getString("street_address_1"),
                            resultSet.getString("street_address_2"),
                            resultSet.getString("city"),
                            resultSet.getString("state_province_region"),
                            resultSet.getString("zip_code"),
                            resultSet.getString("phone_number"));
    }
}
