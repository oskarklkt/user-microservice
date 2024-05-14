# Indexes
Indexes are used to improve the performance of queries. They are used to quickly locate data without having to search every row in a database table every time a database table is accessed. Indexes can be created using one or more columns of a database table, providing the basis for both rapid random lookups and efficient access of ordered records.

## Task Description

Keeping your domain and relational models in mind, think about the cases when you might need an index. Create a script to populate your tables with a big amount of data (couple millions of rows). Check the query execution plan with and without an index, compare the execution times as well.

## Populating tables with dummy data

```sql
DO
$do$
    BEGIN
        FOR i IN 1..2000000 LOOP
                INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date,
                                   client_type)
                VALUES ('John', 'Doe', 'M', '1990-01-01', '123456789', CONCAT(i, '@gmail.com'), 'https://www.google.com', '2021-01-01',
                        'BASIC');
            END LOOP;
    END
$do$;
```


## Searching through 2000000 records using column without index

```sql
EXPLAIN ANALYZE SELECT * FROM users WHERE name = 'Johnson';
```

### Result

```
Gather  (cost=1000.00..44104.02 rows=1 width=97) (actual time=137.377..141.325 rows=1 loops=1)
Workers Planned: 2
Workers Launched: 2
->  Parallel Seq Scan on users  (cost=0.00..43103.92 rows=1 width=97) (actual time=129.014..129.097 rows=0 loops=3)
Filter: ((name)::text = 'Johnson'::text)
Rows Removed by Filter: 666666
Planning Time: 0.080 ms
Execution Time: 141.410 ms
```


## Searching through 2000000 records using column with index

```sql
EXPLAIN ANALYZE SELECT * FROM users WHERE id = 1000002;
```

### Result

```
Index Scan using users_pkey on users  (cost=0.43..8.45 rows=1 width=97) (actual time=0.042..0.081 rows=1 loops=1)
Index Cond: (id = 1000002)
Planning Time: 0.104 ms
Execution Time: 0.192 ms
```