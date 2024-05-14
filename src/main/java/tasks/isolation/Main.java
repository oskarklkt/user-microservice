package tasks.isolation;

import com.griddynamics.user.common.Database;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Database.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
        serializableIsolation();

    }


    public static void serializableIsolation() {
        Connection connection = null;
        Connection connection2 = null;
        PreparedStatement userStatement = null;
        PreparedStatement getNumberOfUsersStatement = null;
        String getNumberOfUsers = "SELECT COUNT(*) FROM users";

        try {
            connection = Database.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection2 = Database.getConnection();
            connection2.setAutoCommit(false);
            userStatement = connection2.prepareStatement("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            connection2.commit();
            getNumberOfUsersStatement = connection.prepareStatement(getNumberOfUsers);
            connection.commit();
            ResultSet rs = getNumberOfUsersStatement.executeQuery();
            while (rs.next()) {
                log.info(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
