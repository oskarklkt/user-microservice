# execute(String query, Object... args) vs execute(String query, Consumer<PreparedStatement> statementConsumer)

---

### execute(String query, Object... args):
```java
@SneakyThrows
public void execute(String query, Object... args) {
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        preparedStatement.execute();
    }
}
```
### Usage: 
```java
@Test
void execute_insert() {
    int initialSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
    userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            "test", "tester", "M", "1990-01-01", "123456789", "test@ex.com", "https://www.google.com", "2021-01-01", "BASIC");
    int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
    assertEquals(initialSize + 1, finalSize);
}
```
### Pros:
- Simplicity - user of this function only needs to provide query and arguments
- Reduces boilerplate code while using 
### Cons:
- No way to set custom parameters for PreparedStatement
- Less Flexible

--- 

### execute(String query, Consumer<PreparedStatement> statementConsumer)
```java
@SneakyThrows
public void execute(String query, Consumer<PreparedStatement> statementConsumer) {
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        statementConsumer.accept(preparedStatement);
        preparedStatement.execute();
    }
}
```
### Usage:
```java
@Test
void execute_overloaded_insert() {
    int initialSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
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
    assertEquals(initialSize + 1, finalSize);
}
```
### Pros:
- Flexibility - user can set custom parameters for PreparedStatement
- More control over PreparedStatement
- Reusability - This approach can be more modular and reusable in scenarios where the same PreparedStatement setup is 
used across different parts of the application. 
The configuration logic can be centralized or shared across multiple execution contexts.
### Cons:
- Complexity of usage
- More boilerplate code
- More error-prone

## Summary:
- If you need a simple and straightforward way to execute a query with arguments, use the first approach.
- If you need more control over the PreparedStatement setup, or if you want to reuse the configuration logic across different parts of the application, use the second approach.
