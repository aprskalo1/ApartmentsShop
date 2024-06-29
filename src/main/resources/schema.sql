-- users and roles
CREATE TABLE "User"
(
    id       INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(30)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Role
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE UserRole
(
    userId INT NOT NULL,
    roleId INT NOT NULL,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES "User" (id),
    FOREIGN KEY (roleId) REFERENCES Role (id)
);

-- apartments and categories
CREATE TABLE Category
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Apartment
(
    id          INT GENERATED ALWAYS AS IDENTITY,
    location    VARCHAR(50)      NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    size        DOUBLE PRECISION NOT NULL,
    rooms       INT              NOT NULL,
    quantity    INT              NOT NULL,
    pictureUrl  VARCHAR(255)     NOT NULL,
    category_id INT              NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES Category (id)
);

-- cart
CREATE TABLE MyCart
(
    id           INT GENERATED ALWAYS AS IDENTITY,
    apartment_id INT              NOT NULL,
    user_id      INT NULL,
    quantity     INT              NOT NULL,
    totalPrice   DOUBLE PRECISION NOT NULL,
    isBought     BOOLEAN          NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (apartment_id) REFERENCES Apartment (id),
    FOREIGN KEY (user_id) REFERENCES "User" (id)
);

--enum
CREATE TYPE purchase_type_enum AS ENUM ('CASH', 'PAYPAL');

-- purchase
CREATE TABLE Purchase
(
    id                INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id           INT                NOT NULL,
    apartment_location VARCHAR(255)      NOT NULL,
    quantity          INT                NOT NULL,
    purchase_date     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    purchase_type     VARCHAR(50)        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "User" (id)
);
