package tasks.inconsistency;

import com.griddynamics.user.common.Database;
import com.griddynamics.user.common.QueryHandler;
import com.griddynamics.user.mapper.modelToDto.UserDtoMapper;
import com.griddynamics.user.model.Address;
import com.griddynamics.user.model.User;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final UserDtoMapper userDtoMapper = new UserDtoMapper();
    private static final QueryHandler<User> userQueryHandler = new QueryHandler<>();
    private static final QueryHandler<Address> addressQueryHandler = new QueryHandler<>();

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Database.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

        with_Transaction();


    }

    public static void without_Transaction() {
        userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", "Bill", "Gates", "M", "1955-10-28", "+123456789", "gates@gmail.com", "https://en.wikipedia.org/wiki/Bill_Gates", "2021-10-28", "BASIC");
        addressQueryHandler.execute("INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", -1, "USA", "One Microsoft Way", "micro", "Redmond", "Washington", "98052", "+123456789");
    }

    public static void with_Transaction() {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement addressStatement = null;

        try {
            connection = Database.getConnection();
            connection.setAutoCommit(false);

            String userQuery = "INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            userStatement = connection.prepareStatement(userQuery);
            userStatement.setString(1, "Bill");
            userStatement.setString(2, "Gates");
            userStatement.setString(3, "M");
            userStatement.setString(4, "1955-10-28");
            userStatement.setString(5, "+123456789");
            userStatement.setString(6, "gates@gmail.com");
            userStatement.setString(7, "https://en.wikipedia.org/wiki/Bill_Gates");
            userStatement.setString(8, "2021-10-28");
            userStatement.setString(9, "BASIC");
            userStatement.execute();

            String addressQuery = "INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            addressStatement = connection.prepareStatement(addressQuery);
            addressStatement.setLong(1, -1);
            addressStatement.setString(2, "USA");
            addressStatement.setString(3, "One Microsoft Way");
            addressStatement.setString(4, "micro");
            addressStatement.setString(5, "Redmond");
            addressStatement.setString(6, "Washington");
            addressStatement.setString(7, "98052");
            addressStatement.setString(8, "+123456789");
            addressStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on error
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (userStatement != null) userStatement.close();
                if (addressStatement != null) addressStatement.close();
                if (connection != null) connection.setAutoCommit(true);
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
