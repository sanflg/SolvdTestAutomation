#Count the country ocurrences in users table greater than 2
SELECT 
    c.tag AS 'Country', COUNT(c.tag) AS 'Occurrences'
FROM
    countries AS c
        LEFT JOIN
    states AS s ON c.id = s.countries_id
        LEFT JOIN
    cities AS ct ON s.id = ct.states_id
        RIGHT JOIN
    users AS u ON u.cities_id = ct.id
GROUP BY c.tag
HAVING COUNT(c.tag) >= 2;
    
#Show the supplier name and average price of its products where the average is bigger than 5
SELECT 
    s.name AS 'Supplier', AVG(p.price) AS 'Average'
FROM
    products AS p
        RIGHT JOIN
    suppliers AS s ON p.suppliers_id = s.id
GROUP BY p.suppliers_id
HAVING AVG(p.price) > 5;

#See how many cities have each country, excluding countries with more than 20 cities
SELECT 
    c.name AS 'Country', COUNT(ct.id) AS 'Cities'
FROM
    countries AS c
        LEFT JOIN
    states AS s ON c.id = s.countries_id
        LEFT JOIN
    cities AS ct ON s.id = ct.states_id
GROUP BY c.name
HAVING COUNT(ct.id) < 20;

#See the average price of each category excluding 'Food' category
SELECT 
    c.name AS 'Category', AVG(price) AS 'Average price'
FROM
    products AS p
        LEFT JOIN
    categories AS c ON p.categories_id = c.id
GROUP BY categories_id
HAVING c.name != 'Food';

#See how many users have each statuses, excluding 'New' status
SELECT 
    s.name AS 'Staus', COUNT(u.id) AS 'Count'
FROM
    users AS u
        RIGHT JOIN
    usersstatuses AS us ON us.users_id = u.id
        INNER JOIN
    statuses AS s ON s.id = us.statuses_id
GROUP BY s.id
HAVING s.name != 'New';
	
#Queries without "GROUP BY" or "HAVING"

#Count the languages.
SELECT 
    COUNT(tag) AS 'Languages'
FROM
    languages;

#See the price of the cheapest product
SELECT 
    MIN(price) AS 'Price'
FROM
    products;

#See the price of the most expensive product
SELECT 
    MAX(price) AS 'Price'
FROM
    products;

#See the sum of all the products prices of supplier with id = 1
SELECT 
    SUM(price) AS 'Total'
FROM
    products AS p
        LEFT JOIN
    suppliers AS s ON s.id = p.suppliers_id
WHERE
    p.suppliers_id = 1;

#See the average price of all products
SELECT 
    AVG(price) AS ' Average price'
FROM
    products;
    