package com.griddynamics.user.mapper.resultsetToModel;

import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.model.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.function.Function;

public class ResultSetUserMapper implements Function<ResultSet, User> {
    @Override
    @SneakyThrows
    public User apply(ResultSet resultSet) {
        return new User(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("gender").equals("M") ? Gender.MALE : Gender.FEMALE,
                resultSet.getString("birthday"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"),
                resultSet.getString("profile_photo_url"),
                resultSet.getString("account_creation_date"),
                resultSet.getString("client_type").equals("BASIC") ? ClientType.BASIC : ClientType.VIP);
    }
}
