CREATE DATABASE OnlineStore;
USE OnlineStore;

CREATE TABLE USER(
User_id int PRIMARY KEY,
Name VARCHAR(30) NOT NULL,
Phone_no BIGINT NULL
);

ALTER TABLE USER
ADD Type VARCHAR(20);

CREATE TABLE Address(
Address_id int PRIMARY KEY,
user_id int,
FOREIGN KEY (User_id) REFERENCES User(User_id),
city VARCHAR(20),
State VARCHAR(20),
country VARCHAR(20) DEFAULT "India",
PinCode INT NOT NULL
);

CREATE TABLE Category(
Category_id int PRIMARY KEY,
Category_Name VARCHAR(30) NOT NULL,
Description VARCHAR(50) NULL
);

CREATE TABLE Product(
Product_id INT PRIMARY KEY,
Category_id INT,
FOREIGN KEY(Category_id) REFERENCES Category(Category_id),
Product_Name VARCHAR(30) NOT NULL,
Price DECIMAL(10,2) NOT NULL,
Product_Description VARCHAR(50) DEFAULT NULL,
Add_time DATETIME DEFAULT current_timestamp,
ISActive Boolean DEFAULT 1,
Stock INT NOT NULL
);

-- ALTER TABLE Product
-- ADD COLUMN Stock INT NOT NULL;

-- ALTER TABLE Product
-- MODIFY COLUMN Stock INT DEFAULT 5;

-- select * from product;


CREATE TABLE Image(
Image_id INT PRIMARY KEY,
Product_id INT,
FOREIGN kEY(Product_id) REFERENCES Product(Product_id),
url VARCHAR(100) NULL
);

ALTER TABLE Image
MODIFY URL VARCHAR(200) DEFAULT NULL;
-- CREATE TABLE Stock(
-- Stock_id INT PRIMARY KEY,
-- Product_id INT,
-- FOREIGN kEY(Product_id) REFERENCES Product(Product_id),
-- Quantity INT NULL
-- );

CREATE TABLE Orders(
Order_id INT PRIMARY KEY,
Product_id INT,
FOREIGN kEY(Product_id) REFERENCES Product(Product_id),
User_id INT,
Quantity INT,
FOREIGN KEY(User_id) REFERENCES User(User_id),
Order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
Total_Amount decimal(10,2) NULL
);

CREATE TABLE Shipping(
Shipping_id INT PRIMARY KEY,
Order_id INT,
FOREIGN kEY(Order_id) REFERENCES Orders(Order_id),
User_id INT,
FOREIGN KEY(User_id) REFERENCES User(User_id),
Address_id INT,
FOREIGN KEY(Address_id) REFERENCES Address(Address_id),
Status Varchar(20) NOT NULL
);

SHOW TABLES;

DROP TABLE Product;