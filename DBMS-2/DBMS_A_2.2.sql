-- Display Id, Title, Category Title, Price of the products which are Active and recently added products should be at top-- 

SELECT 
	p.product_id,
    p.product_name,
    c.category_name,
    p.price
FROM Product AS p
JOIN Category AS c
ON c.category_id = p.category_id
WHERE 
	p.stock > 1
	AND p.IsActive = 1
    ORDER BY p.Add_time DESC;
    
    
-- Display the list of products which don't have any images
SELECT p.Product_id, p.product_name
FROM product AS p
LEFT JOIN Image AS i
ON p.Product_id = i.Product_id
WHERE i.image_id IS NULL;


-- Display all Id, Title and Parent Category Title for all the Categories listed, 
-- sorted by Parent Category Title and then Category Title. 
-- (If Category is top category then Parent Category Title column should display “Top Category” as value.)
SELECT 
	p.product_id,
    p.product_name,
    c.category_name AS "Top Category"
FROM Product AS p
RIGHT JOIN category AS c
ON c.category_id = p.category_id
ORDER BY c.category_name, p.product_name;

-- Display Id, Title, Parent Category Title of all the leaf Categories (categories which are not parent of any other category)
SELECT 
	p.product_id,
    p.product_name,
    c.category_name 
FROM Product AS p  
LEFT JOIN category AS c 
ON c.category_id = p.category_id;

SELECT 
	product_id,
    product_name,
    stock
FROM Product As p
Where stock < 51;
