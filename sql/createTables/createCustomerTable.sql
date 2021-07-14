CREATE TABLE customers(
	id INT AUTO_INCREMENT,
    bank_id INT,
    name VARCHAR(100),
    surname VARCHAR(100),
    username VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    balance INT,
    PRIMARY KEY(id),
    FOREIGN KEY(bank_id) REFERENCES bank(id)
);