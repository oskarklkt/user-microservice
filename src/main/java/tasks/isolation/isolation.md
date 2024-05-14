# Isolation task

## Description
Keeping your domain and relational models in mind, think about the cases when you might need non-default transaction isolation levels. Describe these cases and, using the JDBC code from the previous modules, demonstrate how the system behaves when using the default isolation level and the correct isolation level

---

## Use case 
We want to get number of users at the start of transaction

Default isolation - READ_COMMITTED - if other transaction adds user, our transaction will see it.

Correct isolation - SERIALIZABLE - if other transaction adds user, our transaction will not see it.

## Default isolation
```java
public static void defaultIsolation() {
        Connection connection = null;
        Connection connection2 = null;
        PreparedStatement userStatement = null;
        PreparedStatement getNumberOfUsersStatement = null;
        String getNumberOfUsers = "SELECT COUNT(*) FROM users";

        try {
            connection = Database.getConnection();
            connection.setAutoCommit(false);
            connection2 = Database.getConnection();
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
            getNumberOfUsersStatement = connection.prepareStatement(getNumberOfUsers);
            ResultSet rs = getNumberOfUsersStatement.executeQuery();
            while (rs.next()) {
                log.info(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
```

### Result 
counted added user from different transaction

## Serializable isolation
```java
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
```

### Result
Now it returned 0 - size ad the beginning of transaction

