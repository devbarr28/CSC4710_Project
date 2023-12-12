CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

DROP TABLE IF EXISTS BillsMessages;
DROP TABLE IF EXISTS Bills;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS CounterRequest; -- Changed to proper case
DROP TABLE IF EXISTS Trees;
DROP TABLE IF EXISTS QuoteRequests; -- Changed to proper case
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    creditCard CHAR(16),
    username VARCHAR(50),
    phoneNumber VARCHAR(50),
    UNIQUE (username)
);

CREATE TABLE QuoteRequests ( -- Changed to proper case
    quoteID INTEGER AUTO_INCREMENT PRIMARY KEY,
    clientid INTEGER,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    FOREIGN KEY (clientid) REFERENCES Users (id)
);

CREATE TABLE Trees (
    id INTEGER PRIMARY KEY,
    quoteid INTEGER,
    size DOUBLE,
    height DOUBLE,
    distanceFromHouse DOUBLE,
    FOREIGN KEY (quoteid) REFERENCES QuoteRequests (quoteID)
);

CREATE TABLE CounterRequest (
    id INTEGER PRIMARY KEY,
    userid INTEGER,
    quoteid INTEGER,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    note VARCHAR(200),
    FOREIGN KEY (userid) REFERENCES Users (id),
    FOREIGN KEY (quoteid) REFERENCES QuoteRequests (quoteID)
);

CREATE TABLE Orders (
    id INTEGER PRIMARY KEY,
    quoteid INTEGER,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    FOREIGN KEY (quoteid) REFERENCES QuoteRequests (quoteID)
);

CREATE TABLE Bills (
    id INTEGER PRIMARY KEY,
    orderid INTEGER,
    price DOUBLE,
    discount DOUBLE,
    balance DOUBLE,
    status VARCHAR(20),
    FOREIGN KEY (orderid) REFERENCES Orders (id)
);

CREATE TABLE BillsMessages (
    id INTEGER PRIMARY KEY,
    userid INTEGER,
    billid INTEGER,
    msgtime DATETIME,
    price DOUBLE,
    schedulestart DATETIME,
    scheduleend DATETIME,
    note VARCHAR(200),
    FOREIGN KEY (userid) REFERENCES Users (id),
    FOREIGN KEY (billid) REFERENCES Bills (id)
);
