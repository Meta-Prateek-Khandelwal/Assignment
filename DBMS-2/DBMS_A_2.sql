INSERT INTO User (User_id, Name, phone_no, type)
VALUES (101,"Ajay",9945678654, "Administator"),
(102,"Ram",9945672224, "Shopper"),
(103,"Mohan",9945288654, "Shopper"),
(104,"Sohan",7945678654, "Shopper");

INSERT INTO Address (Address_id, User_id, city, state, country, PinCode)
VALUES (201,101,"Kota", "Rajasthan", "India", 322221),
(202,102,"Jaipur", "Rajasthan", "India",302020),
(203,103, "Pali", "Rajasthan", "India",212232),
(204,104, "Dusaa", "Rajasthan", "India",12321);

INSERT INTO Category (Category_id, Category_Name, description)
VALUES (301,"Sport","Sports items"),
(302,"electronics","electronics item"),
(303,"clothing","clothing items"),
(304,"Drink", "Drink items");

INSERT INTO Product (Product_id, Category_id, Product_Name, price, Product_Description)
VALUES (401,301,"Ball", 50),
(402,302,"Phone",10000),
(403,303,"Sheet",500),
(404,304,"water", 22);

-- SET SQL_SAFE_UPDATES = 0;
-- UPDATE Product
-- SET Stock = 50
-- WHERE stock = 0;
-- SET SQL_SAFE_UPDATES = 1;

DELETE FROM Product WHERE Product_id = 403;
SELECT * FROM Product;

INSERT INTO Orders (Order_id, Product_id, User_id,Order_date, Total_Amount)
VALUES (505,402,102,"2025-03-25",10000),
(506,403,103, "2025-02-25",500),
(507,404,104,"2025-02-20", 22);

INSERT INTO Shipping (Shipping_id, Order_id, User_id, Address_id, status, days)
VALUES (610,502,102,202, "Panding", 16),
(608,503,103, 203, "Panding", 11),
(609,504,104, 204, "Panding", 10);

INSERT INTO Image (Image_id, product_id, url)
VAlUES (1001, 402, NULL),
(1002, 402, NULL),
(1003, 403, NULL),
(1004, 403, NULL);
