--drop tables in correct order
DROP TABLE user;

--create tables
CREATE TABLE user
    (username   VARCHAR(25),
    password    VARBINARY(16),
    wins        INT(3),
    losses      INT(3));

--add PK constraints
ALTER TABLE user
    ADD CONSTRAINT user_username_pk primary key(username);

