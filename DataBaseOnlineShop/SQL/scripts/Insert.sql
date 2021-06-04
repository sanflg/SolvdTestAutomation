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

INSERT INTO Addresses (name, Cities_id) VALUES ('Avenida Siempre Viva', 4);
INSERT INTO Addresses (name, Cities_id) VALUES ('Torfyanoy Pereulok', 13);
INSERT INTO Addresses (name, Cities_id) VALUES ('Narodnaja vulica', 15);
INSERT INTO Addresses (name, Cities_id) VALUES ('Juan B. Justo', 29);
INSERT INTO Addresses (name, Cities_id) VALUES ('Mexico', 34);

INSERT INTO Languages (name, tag) VALUES ('English', 'ENG');
INSERT INTO Languages (name, tag) VALUES ('Belarusian', 'BEL');
INSERT INTO Languages (name, tag) VALUES ('Spanish', 'SPA');

INSERT INTO Categories (name) VALUES ('Food');
INSERT INTO Categories (name) VALUES ('Games');
INSERT INTO Categories (name) VALUES ('Technology');
INSERT INTO Categories (name) VALUES ('House');

INSERT INTO Currencies (name, tag) VALUES ('Dollar', 'USD');
INSERT INTO Currencies (name, tag) VALUES ('Ruble', 'BYR');
INSERT INTO Currencies (name, tag) VALUES ('Peso', 'ARS');

INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('Juan2mvo', '123123', 'Juan@gmail.com', 'Juan', 'Perez', '1996-08-13', 3);
INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('Pepepepe', 'asdasd', 'Pepitox@gmail.com' ,'Pepe', 'Ramirez', '1982-08-13', 3);
INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('JoJo', 'Isthisareference?', 'JoJo@gmail.com' ,'Jonathan', 'Joestar', '1835-04-15', 1);
INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('JoJo2', 'Isthisareference?', 'Jojo2@gmail.com' ,'Joseph', 'Joestar', '1935-01-27', 1);
INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('Darya2512', 'Jask25jL.s.aj2', 'Darya@gmail.com' ,'Darya', 'Azarenka', '2000-12-03', 2);
INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id)
	VALUES ('Ivannnn', 'aslK)#(3kfqpx.', 'Ivan@gmail.com' ,'Ivan', 'Kavalski', '1992-09-13', 2);
    
INSERT INTO Carts (Individuals_id) VALUES (1);
INSERT INTO Carts (Individuals_id) VALUES (2);
INSERT INTO Carts (Individuals_id) VALUES (3);
INSERT INTO Carts (Individuals_id) VALUES (4);
INSERT INTO Carts (Individuals_id) VALUES (5);
INSERT INTO Carts (Individuals_id) VALUES (6);

INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (1, 1, 0, 1);
INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (0, 0, 1, 2);
INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (0, 0, 0, 3);
INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (0, 1, 0, 4);
INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (1, 1, 0, 5);
INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (0, 0, 0, 6);

INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (1512, 1, 1);
INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (162, 1, 2);
INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (915, 2, 3);
INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (519, 3, 4);
INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (19, 4, 5);
INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (2621, 5, 6);

INSERT INTO Suppliers (Individuals_id) VALUES (1);
INSERT INTO Suppliers (Individuals_id) VALUES (3);
INSERT INTO Suppliers (Individuals_id) VALUES (5);

INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (31151231, 1);
INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (85182931, 2);
INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (96910236, 3);
INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (01069122, 4);
INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (00105192, 5);
INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (68190239, 6);

INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Lettuce', 1.25, 1, 3, 2);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Pork', 3.15, 1, 2, 2);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Minecraft', 25.99, 2, 3, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Inmost', 10.00, 3, 1, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Cooler', 7.10, 3, 2, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('PC', 150.00, 3, 2, 3);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Chair', 15.50, 4, 1, 1);
INSERT INTO Products (name, price, categories_id, currencies_id, suppliers_id) VALUES ('Table', 25.50, 4, 3, 1);

INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (3, 1, 3);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (3, 2, 1);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (1, 1, 2);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (2, 3, 6);
INSERT INTO productcarts (Carts_id, quantity, Products_id) VALUES (5, 1, 4);

INSERT INTO orders (Carts_id) VALUES (1);

INSERT INTO productorders(Orders_id, Products_id) VALUES (1, 2);
INSERT INTO productorders(Orders_id, Products_id) VALUES (1, 1);
INSERT INTO productorders(Orders_id, Products_id) VALUES (1, 5);