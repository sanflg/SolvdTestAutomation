DELETE FROM individuals 
WHERE
    id = 6;

UPDATE individuals 
SET 
    languages_id = 1
WHERE
    languages_id = 3;

DELETE FROM languages 
WHERE
    name = 'Spanish';

DELETE FROM countries 
WHERE
    id = 3;

DELETE FROM individuals 
WHERE
    id = 5;

UPDATE products 
SET 
    currencies_id = 1
WHERE
    currencies_id = 3;

DELETE FROM currencies 
WHERE
    id = 3;

UPDATE individuals 
SET 
    languages_id = 2
WHERE
    languages_id = 1 AND username = 'JoJo';

UPDATE individuals 
SET 
    first_name = 'Pepito'
WHERE
    email = 'Darya@gmail.com'
        OR last_name = 'Kavalsi';

UPDATE products 
SET 
    name = 'Lllletttuceee'
WHERE
    categories_id = 1
        OR currencies_id = 1 AND suppliers_id = 2;

UPDATE categories 
SET 
    name = 'For the House'
WHERE
    name = 'House';

UPDATE currencies 
SET 
    tag = 'Usd'
WHERE
    id = 1;

UPDATE currencies 
SET 
    tag = 'Byr'
WHERE
    name = 'Ruble';