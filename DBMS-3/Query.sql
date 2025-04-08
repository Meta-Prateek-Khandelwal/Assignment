-- With respect to StoreFront application identify, apply and list the constraints you would apply on the columns for the tables created.--  

-- Display the list of products (Id, Title, Count of Categories) which fall in more than one Category
SELECT 
	Product_id,
    Count(product_name)
FROM product
GROUP BY product_id
Having product_id > 1;

-- Display Count of products as per below price range
SELECT "0 to 100" AS price_range, COUNT(*) AS "Count of products "
FROM Product
WHERE price Between 0 AND 100
UNION ALL
SELECT "100 to 500" AS price_range,COUNT(*) AS "Count of products"
FROM Product
WHERE price Between 100 AND 500
UNION All
SELECT "Above 500" AS price_range,COUNT(*) AS "Count of products"
FROM Product
WHERE price > 500;



-- Display the Categories along with number of products under each category.
SELECT 
	  c.category_id,
    c.category_name,
    SUM(p.stock) AS "number of products"
FROM category As c
JOIN Product As p
ON p.category_id = c.category_id
GROUP BY p.category_id;
    
    
-- ASSIGNMENT-3
-- Display Shopper’s information along with number of orders he/she placed during last 30 days.
-- r
SELECT 
  u.user_id,
  u.name,
  u.phoneNo,
  COUNT(o.Orders_id) AS orderCount
FROM User AS u
JOIN Orders AS o
ON u.user_id = o.user_id
AND o.order_date >= DATE_SUB(curdate(), interval 30 DAY)
GROUP BY u.user_id, u.Name, u.phoneNo;

-- Display the top 3 Shoppers who generated maximum number of order in last 30 days.
SELECT 
  u.user_id,
  u.name,
  u.phoneNo,
  COUNT(o.Orders_id) AS orderCount
FROM User AS u
JOIN Orders AS o
ON u.user_id = o.user_id
AND o.order_date >= DATE_SUB(curdate(), interval 30 DAY)
GROUP BY u.user_id
ORDER BY orderCount DESC
LIMIT 3;

-- Display top 5 Products which are ordered most in last 60 days along with numbers.
SELECT 
  c.product_id,
  p.product_name,
  SUM(c.Quantity) AS TrandingProduct
FROM Orders AS o
JOIN Cart AS c ON o.Cart_id = c.Cart_id
JOIN Product AS p ON c.product_id = p.product_id
WHERE o.order_date >= DATE_SUB(curdate(), interval 60 DAY)
GROUP BY c.product_id
ORDER BY TrandingProduct DESC
LIMIT 5;

-- Display Monthly sales revenue of the StoreFront for last 6 months. It should display each month’s sale
SELECT 
	date_format(order_date, '%Y-%m') AS Month,
	Sum(total_Amount) AS monthly_revenue
FROM orders
WHERE Order_date >= DATE_SUB(curdate(), INTERVAL 6 MONTH)
GROUP BY Month
order by Month ASC;


-- Mark the products as Inactive which are not ordered in last 90 days.
UPDATE product
SET ISActive = 0
WHERE product_id NOT IN (
	SELECT DISTINCT c.product_id
	FROM cart AS c
    JOIN orders AS o ON o.cart_id = c.cart_id
    WHERE o.order_date >= NOW() - INTERVAL 90 DAY
    );
    
-- Given a category search keyword, display all the Products present in this category/categories.
SELECT c.Category_Name, p.product_Name
FROM category AS c
LEFT JOIN Product AS p
ON c.Category_id = p.product_id;

-- Display top 1 Items which were cancelled most.
SELECT * FROM Orders
WHERE status = "canceled";

-- Assignment 4:
-- Consider a form where providing a Zip Code populates associated City and
-- State. 
-- Create appropriate tables and relationships for the same and write a SQL
--         query for that returns a Resultset containing Zip Code, City Names and
--         States ordered by State Name and City Name.
-- 	(Create 3 tables to store State, District/City & Zip code separately)

SELECT 
  City,
  State
FROM Address
WHERE pinCode = 322221;

SELECT 
  City,
  State,
  pinCode
FROM Address
order by State, city;


CREATE TABLE stateAddress  SELECT Address_id AS State_id, State FROM Address;
CREATE TABLE cityAddress  SELECT Address_id AS city_id,city FROM Address;
CREATE TABLE pinAddress  SELECT Address_id AS pinCode_id,pinCode FROM Address;
SELECT * FROM stateAddress;
SELECT * FROM cityAddress;
SELECT * FROM pinAddress;


-- Assignment 5:
-- Create a view displaying the order information (Id, Title, Price, Shopper’s name, Email, Orderdate, Status) with latest ordered items should be displayed first for last 60 days.
CREATE VIEW OrderInformation
AS SELECT 
  o.Orders_id,
  p.Product_Name AS Title,
  p.Price,
  u.name ,
  u.phoneNo,
  o.order_date,
  o.Status
FROM Orders  O
JOIN Cart  c
ON o.Cart_id = c.Cart_id
JOIN Product  P
ON c.Product_id = p.Product_id
JOIN user  u
ON o.user_id = u.user_id
WHERE o.order_date >= DATE_SUB(curdate(), interval 60 DAY)
ORDER BY o.order_date DESC;
DROP View OrderInformation;
SELECT * FROM OrderInformation;

-- Use the above view to display the Products(Items) which are in ‘shipped’ state.
SELECT Title FROM OrderInformation
where status = "Delived";

-- Use the above view to display the top 2 most selling products.
SELECT Title, price
FROM OrderInformation
WHERE status != "canceled"
ORDER BY price DESC
LIMIT 2;
