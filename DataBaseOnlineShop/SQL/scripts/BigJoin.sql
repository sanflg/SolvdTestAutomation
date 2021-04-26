/* What are the countries tags, states names, cities names and usernames
of the persons who bought at least 1 product of the supplier 
with name = 'Distributor SA'?*/

SELECT 
    c.tag AS 'Country',
    s.name AS 'Region',
    ct.name AS 'City',
    u.username AS 'Username'
FROM
    countries AS c
        LEFT JOIN
    states AS s ON c.id = s.countries_id
        LEFT JOIN
    cities AS ct ON s.id = ct.states_id
        RIGHT JOIN
    users AS u ON u.cities_id = ct.id
        INNER JOIN
    carts ON carts.users_id = u.id
        RIGHT JOIN
    productcarts AS p ON p.carts_id = carts.id
        LEFT JOIN
    products AS pr ON p.products_id = pr.id
        LEFT JOIN
    suppliers AS su ON pr.suppliers_id = su.id
WHERE
    su.name = 'Distributor SA';