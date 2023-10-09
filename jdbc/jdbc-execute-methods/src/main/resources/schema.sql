INSERT INTO customer (name, address) VALUES ('Kasun', 'Galle'),
                                            ('Nuwan', 'Matara'),
                                            ('Ruwan', 'Kandy');
SELECT * FROM customer;
DELETE FROM customer WHERE address = 'Galle';
SELECT COUNT(*) FROM customer;      /* 2 */
SELECT * FROM customer;

CREATE TABLE IF NOT EXISTS customer(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS item(
    code VARCHAR(20) PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    qty INT NOT NULL,
    unit_price DECIMAL(8,2) NOT NULL
);