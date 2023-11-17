CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

DROP TABLE IF EXISTS BillsMessages;
DROP TABLE IF EXISTS Bills;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS QuotesMessages;
DROP TABLE IF EXISTS Trees;
DROP TABLE IF EXISTS quoteRequest;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id INTEGER AUTO_INCREMENT,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    creditCard CHAR(16),
    username VARCHAR(50),
    phoneNumber VARCHAR(50),
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE quoteRequest (
    quoteID INTEGER AUTO_INCREMENT,
    clientid INTEGER,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    PRIMARY KEY (quoteID),
    FOREIGN KEY (clientid) REFERENCES Users (id)
);

CREATE TABLE Trees (
    id INTEGER,
    quoteid INTEGER,
    size DOUBLE,
    height DOUBLE,
    distanceFromHouse DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (quoteid) REFERENCES QuoteRequest (quoteID)
);

CREATE TABLE QuotesMessages (
    id INTEGER,
    userid INTEGER,
    quoteid INTEGER,
    msgtime DATETIME,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    note VARCHAR(200),
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES Users (id),
    FOREIGN KEY (quoteid) REFERENCES quoteRequest (quoteID)
);

CREATE TABLE Orders (
    id INTEGER,
    quoteid INTEGER,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (quoteid) REFERENCES quoteRequest (quoteID)
);

CREATE TABLE Bills (
    id INTEGER,
    orderid INTEGER,
    price DOUBLE,
    discount DOUBLE,
    balance DOUBLE,
    status VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (orderid) REFERENCES Orders (id)
);

CREATE TABLE BillsMessages (
    id INTEGER,
    userid INTEGER,
    billid INTEGER,
    msgtime DATETIME,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    note VARCHAR(200),
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES Users (id),
    FOREIGN KEY (billid) REFERENCES Bills (id)
);
