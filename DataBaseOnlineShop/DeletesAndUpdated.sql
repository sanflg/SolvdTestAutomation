DELETE FROM users 
WHERE
    id = 1;

UPDATE users 
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

DELETE FROM users 
WHERE
    cities_id = 1;

DELETE FROM users 
WHERE
    id = 2;

DELETE FROM statuses 
WHERE
    name = 'Unverified';

DELETE FROM usersstatuses 
WHERE
    users_id = 4 AND states_id = 1;

DELETE FROM suppliers 
WHERE
    name = 'Distributor 4';

UPDATE users 
SET 
    users.cities_id = 1
WHERE
    cities_id IS NULL;

UPDATE products 
SET 
    currencies_id = 1
WHERE
    currencies_id = 3;

DELETE FROM currencies 
WHERE
    id = 3;

UPDATE users 
SET 
    languages_id = 2
WHERE
    languages_id = 1 AND username = 'JoJo';

UPDATE users 
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