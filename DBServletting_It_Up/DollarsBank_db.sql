CREATE DATABASE DollarsBank_db;
USE DollarsBank_db;

CREATE TABLE users (
user_id int auto_increment PRIMARY KEY,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL,
username varchar(30) NOT NULL,
pass varchar(30) NOT NULL,
pin varchar(4) NOT NULL,
cash DECIMAL(10,2) default 0.00
);

CREATE TABLE transactions (
trans_id int auto_increment PRIMARY KEY,
trans_date date NOT NULL,
trans_type varchar(30) NOT NULL,
trans_amount  DECIMAL(10,2) default 0.00,
user_id int NOT NULL,
FOREIGN KEY (user_id) references
users(user_id)
);

INSERT INTO users VALUES(null, 'Rashaad', 'gray', 'rgray', 'pass', '0123', 100.00);
INSERT INTO transactions VALUES(null, curdate(), 'deposit', 100.00, 1);

INSERT INTO users VALUES(null, 'John', 'doe', 'jdoe', 'pass', '1234', 200.00);
INSERT INTO transactions VALUES(null, curdate(), 'deposit', 200.00, 2);