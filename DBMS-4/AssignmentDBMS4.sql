-- Create a function to calculate number of orders in a month. Month and year will be input parameter to function.-- 
DELIMITER $$

CREATE FUNCTION numberOfOrder(order_month int , order_year int) RETURNS int
deterministic
BEGIN 
DECLARE number_of_orders int;
SELECT COUNT(order_id) into number_of_orders
From orders
WHERE month(order_date) = order_month AND 
year(order_date) = order_year;

return number_of_orders;
END $$

DELIMITER $$

select onlinestore.number_of_order(3 , 2025);

-- Create a function to return month in a year having maximum orders. Year will be input parameter.

DELEMITER$$

CREATE FUNCTION maximumOrderMonth(years int) RETURNS int
DETERMINISTIC 
BEGIN
DECLARE maxOrderMonth int;
SELECT month(order_date) AS months into maxOrderMonth
FROM Orders
WHERE Year(order_date) = years
GROUP BY months
ORDER BY count(order_id) DESC
LIMIT 1 ;

return maxOrderMonth;
END$$

DELIMITER $$

select * from orders ;

-- Create a Stored procedure to retrieve average sales of each product in a month. Month and year will be input parameter to function.
DELIMITER$$
CREATE procedure averageSales(in months int, in years int)

BEGIN
SELECT product_id,AVG(Total_amount)
FROM Orders
WHERE month(order_date) = months
AND year(order_date) = years
GROUP BY product_id;
END$$

DELIMITER ;

--Create a stored procedure to retrieve table having order detail with status for a given period. Start date and end date will be input parameter. Put validation on input dates like start date is less than end date. If start date is greater than end date take first date of month as start date. 
DELIMITER $$

CREATE Procedure details(in start_date date, end_date date)

BEGIN
 
IF start_date > end_date THEN
	SET start_date = DATE_FORMAT(NOW(),"%y-%m-01");
END IF;

SELECT order_id, user_id, order_date, total_Amount
FROM Orders
Where date(order_date) BETWEEN start_date AND end_date;
END$$
DELIMITER ; 

DROP Procedure details;
SELECT * FROM orders;

-- Identify the columns require indexing in order, product, category tables and create indexes.
-- orders table 
CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_orders__date ON orders(order_date);
CREATE INDEX idx_orders_status ON orders(status);

-- product table
CREATE INDEX idx_product_category ON product(category_id);
CREATE INDEX idx_product_price ON product(price);
CREATE INDEX idx_product_name ON product(product_name);

-- category table
CREATE INDEX idx_category_parent_id ON category(parent_id);
CREATE INDEX idx_category_name ON category(Category_Name);

-- DROP Index idx_product_category ON Orders;
SHOW INDEXES FROM Orders;
SHOW INDEXES FROM Product;
SHOW INDEXES FROM Category;