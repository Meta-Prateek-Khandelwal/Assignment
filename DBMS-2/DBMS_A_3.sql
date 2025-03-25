SELECT 
	Order_id,
    Order_date,
    Total_Amount
FROM Orders
ORDER BY order_date DESC
LIMIT 2;

SELECT *
FROM orders
ORDER BY Total_Amount DESC
LIMIT 1;

SELECT *
FROM Shipping
WHERE days > 10 
AND status = "Panding";

-- Display list of shoppers which haven't ordered anything since last month
SELECT 
	u.user_id,
    u.name,
    u.phone_no
FROM USER AS u
JOIN Orders AS o
ON u.user_id = o.user_id
AND o.order_date >= DATE_SUB(curdate(), interval 1 MONTH)
WHERE u.Type = "Shopper";

-- Display list of shopper along with orders placed by them in last 15 days
SELECT 
	u.user_id,
    u.name,
    u.phone_no,
    o.order_date
FROM USER AS u
JOIN Orders AS o
ON u.user_id = o.user_id
AND o.order_date >= DATE_SUB(curdate(), interval 15 day)
WHERE u.Type = "Shopper";
  
-- Display list of order items which are in “shipped” state for particular Order Id (i.e.: 1020))
SELECT 
	o.order_id,
    o.product_id,
    p.product_name,
    o.quantity,
    p.price,
    s.status
FROM Orders AS O
JOIN Shipping AS s
ON s.order_id = o.order_id
JOIN Product As p
ON p.product_id = o.product_id
WHERE status = "Delived"
AND shipping_id = 606;

-- Display list of order items along with order placed date which fall between Rs 20 to Rs 50 price.
SELECT 
	o.order_id,
    o.order_date,
    o.product_id,
    p.product_name,
    o.quantity,
    p.price
FROM Orders AS O
JOIN Product As p
ON p.product_id = o.product_id
WHERE p.price BETWEEN
20 AND 50;