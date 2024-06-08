-- default users and roles
INSERT INTO "User"(username, password) VALUES('user', '$2a$10$xszlj1pbFnz6kKreb3X9pugjNUg0MAcC1ff/NR43oKNthJYYcCRj6'); --password
INSERT INTO "User"(username, password) VALUES('admin', '$2a$10$xszlj1pbFnz6kKreb3X9pugjNUg0MAcC1ff/NR43oKNthJYYcCRj6'); --password
INSERT INTO "User"(username, password) VALUES('read_only', '$2a$10$xszlj1pbFnz6kKreb3X9pugjNUg0MAcC1ff/NR43oKNthJYYcCRj6'); --password

INSERT INTO Role(name) VALUES('USER');
INSERT INTO Role(name) VALUES('ADMIN');
INSERT INTO Role(name) VALUES('READ_ONLY');

INSERT INTO UserRole(userId, roleId) VALUES(1, 1);
INSERT INTO UserRole(userId, roleId) VALUES(2, 1);
INSERT INTO UserRole(userId, roleId) VALUES(2, 2);
INSERT INTO UserRole(userId, roleId) VALUES(3, 3);

-- dummy categories and apartments
INSERT INTO Category(name) VALUES('Small');
INSERT INTO Category(name) VALUES('Medium');
INSERT INTO Category(name) VALUES('Big');

INSERT INTO Apartment(location, price, size, rooms, quantity, pictureUrl, category_id) VALUES('New York', 1500.00, 30.0, 2, 5, 'http://example.com/ny.jpg', 1);
INSERT INTO Apartment(location, price, size, rooms, quantity, pictureUrl, category_id) VALUES('Los Angeles', 2500.00, 50.0, 3, 3, 'http://example.com/la.jpg', 2);
INSERT INTO Apartment(location, price, size, rooms, quantity, pictureUrl, category_id) VALUES('Chicago', 3500.00, 70.0, 4, 2, 'http://example.com/chicago.jpg', 3);
INSERT INTO Apartment(location, price, size, rooms, quantity, pictureUrl, category_id) VALUES('Miami', 2000.00, 40.0, 2, 4, 'http://example.com/miami.jpg', 2);
INSERT INTO Apartment(location, price, size, rooms, quantity, pictureUrl, category_id) VALUES('San Francisco', 3000.00, 60.0, 3, 1, 'http://example.com/sf.jpg', 3);