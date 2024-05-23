package com.griddynamics.user.datasource;



import com.griddynamics.user.mapper.resultsetToModel.ResultSetAddressMapper;
import com.griddynamics.user.mapper.resultsetToModel.ResultSetUserMapper;
import com.griddynamics.user.model.Address;
import com.griddynamics.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(QueryHandlerTest.TestConfiguration.class)
@ActiveProfiles("local")
@TestPropertySource("classpath:application-local.properties")
class QueryHandlerTest {

    @Configuration
    @ComponentScan("com.griddynamics.user")
    @Profile("local")
    static class TestConfiguration {}


    @Autowired
    private QueryHandler<User> userQueryHandler;

    @Autowired
    private QueryHandler<Address> addressQueryHandler;

    @Autowired
    private ResultSetUserMapper resultSetUserMapper;

    @Autowired
    private ResultSetAddressMapper resultSetAddressMapper;


    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void execute() {
        userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "test", "tester", "M", "1990-01-01", "123456789", "test@ex.com", "https://www.google.com", "2021-01-01", "BASIC");
        int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        assertEquals(4, finalSize);
    }

    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void testExecute() {
        userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, "test2");
                        preparedStatement.setString(2, "tester2");
                        preparedStatement.setString(3, "M");
                        preparedStatement.setString(4, "1990-01-01");
                        preparedStatement.setString(5, "123456789");
                        preparedStatement.setString(6, "test@ex2.com");
                        preparedStatement.setString(7, "https://www.google.com");
                        preparedStatement.setString(8, "2021-01-01");
                        preparedStatement.setString(9, "BASIC");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
        int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        assertEquals(3, finalSize);

    }

    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void findOne() {
        int initialSize = addressQueryHandler.findMany("SELECT * FROM addresses", resultSetAddressMapper).size();
        addressQueryHandler.execute("DELETE FROM addresses WHERE id = ?", 2L);
        int finalSize = addressQueryHandler.findMany("SELECT * FROM addresses", resultSetAddressMapper).size();
        assertEquals(initialSize - 1, finalSize);

    }

    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void findMany() {
        List<User> users = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper);
        assertEquals("John",users.get(0).getName());
    }

    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void findOneAddress() {
        String city = addressQueryHandler.findOne("SELECT * FROM addresses WHERE id = ?", resultSetAddressMapper, 1L).getCity();
        assertEquals("New York", city);
    }

    @Test
    @Sql(scripts = "classpath:delete_init.sql")
    void findManyAddresses() {
        List<Address> addresses = addressQueryHandler.findMany("SELECT * FROM addresses", resultSetAddressMapper);
        assertEquals(1, addresses.size());
    }

}