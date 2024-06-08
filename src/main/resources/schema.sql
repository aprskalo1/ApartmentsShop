-- default users and roles
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