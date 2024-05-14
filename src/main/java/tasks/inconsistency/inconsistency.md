# Inconsistency task 

---

## Description
Keeping your domain and relational models in mind, and, using the JDBC code from the previous modules, implement the code to bring the state of the database to the inconsistent state (C in ACID). Describe the case and demonstrate how system behaves when using and when not using transactions
 
---

## Information
We want to add user and his address, only when both are added successfully. If one of them fails, we want to rollback the transaction.

## Without transaction

```java
public static void without_Transaction() {
    userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", "Bill", "Gates", "M", "1955-10-28", "+123456789", "gates@gmail.com", "https://en.wikipedia.org/wiki/Bill_Gates", "2021-10-28", "BASIC");
    addressQueryHandler.execute("INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", -1, "USA", "One Microsoft Way", "micro", "Redmond", "Washington", "98052", "+123456789");
}
```

Result: User is added, but address is not added (It fails because id is -1).

## With transaction

```java
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
            ...
```

Result: Both user and address are not added (It fails because id is -1). - That's the expected behavior.