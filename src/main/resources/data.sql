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