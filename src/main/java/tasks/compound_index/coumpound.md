# Multi-column Index

Multi-column index is useful when we need to search for data using multiple columns. 
It is a type of index that is created on multiple columns of a table. It is also known as a composite index.

we can create it like this:

```sql
CREATE INDEX idx_surname_name_email ON users(surname, name, email);
```
