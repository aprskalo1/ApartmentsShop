-- users and roles
CREATE TABLE "User"(
    id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Role(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE UserRole(
    userId INT NOT NULL,
    roleId INT NOT NULL,
    PRIMARY KEY(userId, roleId),
    FOREIGN KEY(userId) REFERENCES "User" (id),
    FOREIGN KEY(roleId) REFERENCES Role (id)
);

-- apartments and categories
CREATE TABLE Category (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Apartment (
    id INT GENERATED ALWAYS AS IDENTITY,
    location VARCHAR(50) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    size DOUBLE PRECISION NOT NULL,
    rooms INT NOT NULL,
    quantity INT NOT NULL,
    pictureUrl VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES Category (id)
);