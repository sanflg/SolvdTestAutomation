INSERT INTO Countries (name, tag) VALUES ('United States', 'US');
INSERT INTO Countries (name, tag) VALUES ('Belarus', 'BY');
INSERT INTO Countries (name, tag) VALUES ('Argentina', 'AR');

#USA states
INSERT INTO States (name, Countries_id) VALUES ('Alabama', 1);
INSERT INTO States (name, Countries_id) VALUES ('Arizona', 1);
INSERT INTO States (name, Countries_id) VALUES ('California', 1);
INSERT INTO States (name, Countries_id) VALUES ('Florida', 1);
INSERT INTO States (name, Countries_id) VALUES ('Idaho', 1);
INSERT INTO States (name, Countries_id) VALUES ('Indiana', 1);
#Belrus states
INSERT INTO States (name, Countries_id) VALUES ('Brest', 2);
INSERT INTO States (name, Countries_id) VALUES ('Gomel', 2);
INSERT INTO States (name, Countries_id) VALUES ('Grodno', 2);
INSERT INTO States (name, Countries_id) VALUES ('Mogilev', 2);
INSERT INTO States (name, Countries_id) VALUES ('Minsk', 2);
INSERT INTO States (name, Countries_id) VALUES ('Vitebsk', 2);
#Argentina states
INSERT INTO States (name, Countries_id) VALUES ('Buenos Aires', 3);
INSERT INTO States (name, Countries_id) VALUES ('Córdoba', 3);
INSERT INTO States (name, Countries_id) VALUES ('Chaco', 3);
INSERT INTO States (name, Countries_id) VALUES ('Corrientes', 3);
INSERT INTO States (name, Countries_id) VALUES ('Misiones', 3);

#USA cities
INSERT INTO Cities (name, States_id) VALUES ('Birmingham', 1);
INSERT INTO Cities (name, States_id) VALUES ('Huntsville', 1);
INSERT INTO Cities (name, States_id) VALUES ('Phoenix', 2);
INSERT INTO Cities (name, States_id) VALUES ('Tucson', 2);
INSERT INTO Cities (name, States_id) VALUES ('Los Angeles', 3);
INSERT INTO Cities (name, States_id) VALUES ('San Francisco', 3);
INSERT INTO Cities (name, States_id) VALUES ('Jacksonville', 4);
INSERT INTO Cities (name, States_id) VALUES ('Miami', 4);
INSERT INTO Cities (name, States_id) VALUES ('Boise', 5);
INSERT INTO Cities (name, States_id) VALUES ('Meridian', 5);
INSERT INTO Cities (name, States_id) VALUES ('Indianapolis', 6);
INSERT INTO Cities (name, States_id) VALUES ('Fort Wayne', 6);
#Belarus cities
INSERT INTO Cities (name, States_id) VALUES ('Brest', 7);
INSERT INTO Cities (name, States_id) VALUES ('Baranavichy', 7);
INSERT INTO Cities (name, States_id) VALUES ('Gomel', 8);
INSERT INTO Cities (name, States_id) VALUES ('Mazyr', 8);
INSERT INTO Cities (name, States_id) VALUES ('Grodno', 9);
INSERT INTO Cities (name, States_id) VALUES ('Lida', 9);
INSERT INTO Cities (name, States_id) VALUES ('Mogilev', 10);
INSERT INTO Cities (name, States_id) VALUES ('Babruysk', 10);
INSERT INTO Cities (name, States_id) VALUES ('Barysaw', 11);
INSERT INTO Cities (name, States_id) VALUES ('Salihorsk', 11);
INSERT INTO Cities (name, States_id) VALUES ('Vitebsk', 12);
INSERT INTO Cities (name, States_id) VALUES ('Orsha', 12);
#Argentina cities
INSERT INTO Cities (name, States_id) VALUES ('La Plata', 13);
INSERT INTO Cities (name, States_id) VALUES ('Merlo', 13);
INSERT INTO Cities (name, States_id) VALUES ('Cordoba', 14);
INSERT INTO Cities (name, States_id) VALUES ('Carlos Paz', 14);
INSERT INTO Cities (name, States_id) VALUES ('Resistencia', 15);
INSERT INTO Cities (name, States_id) VALUES ('Fontana', 15);
INSERT INTO Cities (name, States_id) VALUES ('Corrientes', 16);
INSERT INTO Cities (name, States_id) VALUES ('Santa Ana', 16);
INSERT INTO Cities (name, States_id) VALUES ('Posadas', 17);
INSERT INTO Cities (name, States_id) VALUES ('Obera', 17);

#Languages
INSERT INTO Languages (name, tag) VALUES ('English', 'ENG');
INSERT INTO Languages (name, tag) VALUES ('Belarusian', 'BEL');
INSERT INTO Languages (name, tag) VALUES ('Spanish', 'SPA');

#Categories
INSERT INTO Categories (name) VALUES ('Food');
INSERT INTO Categories (name) VALUES ('Games');
INSERT INTO Categories (name) VALUES ('Tecnology');
INSERT INTO Categories (name) VALUES ('House');

#Currencies
INSERT INTO Currencies (name, tag) VALUES ('Dollar', 'USD');
INSERT INTO Currencies (name, tag) VALUES ('Ruble', 'BYR');
INSERT INTO Currencies (name, tag) VALUES ('Peso', 'ARS');

#Statuses
INSERT INTO Statuses (name) VALUES ('Admin');
INSERT INTO Statuses (name) VALUES ('Banned');
INSERT INTO Statuses (name) VALUES ('Unverified');
INSERT INTO Statuses (name) VALUES ('Premium');
INSERT INTO Statuses (name) VALUES ('New');

#Suppliers
INSERT INTO Suppliers (name, adress, adress_num, cities_id ) VALUES ('Ikea', 'A place', 1412, 15);
INSERT INTO Suppliers (name, adress, adress_num, cities_id ) VALUES ('Ace Endico Corp', 'A place2', 314, 2);
INSERT INTO Suppliers (name, adress, adress_num, cities_id ) VALUES ('Distributor SA', 'A Street', 5151, 31);
INSERT INTO Suppliers (name, adress, adress_num, cities_id ) VALUES ('Distributor 4', 'A Street', 1313, 31);

#Users (Carts are created using a trigger for each new user)
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('Juan2mvo', '123123', 'Juan@gmail.com' ,'Juan', 'Perez', '1996-08-13', 'Juan B. Justo', 1612, 3, 29);
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('Pepepepe', 'asdasd', 'Pepitox@gmail.com' ,'Pepe', 'Ramirez', '1982-08-13', 'Juan B. Justo', 612, 3, 28);
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('JoJo', 'Isthisareference?', 'JoJo@gmail.com' ,'Jonathan', 'Joestar', '1835-04-15', 'A Place', 151, 1, 4);
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('JoJo2', 'Isthisareference?', 'Jojo2@gmail.com' ,'Joseph', 'Joestar', '1935-01-27', 'Another Place', 6123, 1, 1);
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('Darya2512', 'Jask25jL.s.aj2', 'Darya@gmail.com' ,'Darya', 'Azarenka', '2000-12-03', 'A Belarusian Street', 1512, 2, 17);
INSERT INTO users (username, password, email, first_name, last_name, birth_date, adress, adress_num, languages_id, cities_id)
	VALUES ('Ivannnn', 'aslK)#(3kfqpx.', 'Ivan@gmail.com' ,'Ivan', 'Kavalski', '1992-09-13', 'Another Belarusian Street', 251, 2, 18);
    
#Products
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Lettuce', 1.25, 1, 3, 2);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Pork', 3.15, 1, 2, 2);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Minecraft', 25.99, 2, 3, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Inmost', 10.00, 3, 1, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Cooler', 7.10, 3, 2, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('PC', 150.00, 3, 2, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Chair', 15.50, 4, 1, 1);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Table', 25.50, 4, 3, 1);

#ProductsCarts
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (3, 1, 3);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (3, 2, 1);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (1, 1, 2);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (2, 3, 6);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (5, 1, 4);

#UserStatuses
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (1 , 3);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (3 , 1);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (5 , 6);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (1 , 4);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (1 , 2);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (2 , 2);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (1 , 1);
INSERT INTO usersstatuses (Statuses_id, Users_id) VALUES (1 , 5);