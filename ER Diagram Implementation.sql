CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Role VARCHAR(20) NOT NULL
);


CREATE TABLE Client (
    ClientID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100),
    CreditCard VARCHAR(16),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(50) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE QuoteRequest (
    RequestID INT AUTO_INCREMENT PRIMARY KEY,
    ClientID INT NOT NULL,
    Size DECIMAL(10, 2),
    Location VARCHAR(100),
    HouseProximity VARCHAR(50),
    Note TEXT,
    Status VARCHAR(20),
    Timestamp TIMESTAMP,
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID)
);


CREATE TABLE QuoteResponse (
    ResponseID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT NOT NULL,
    DavidNotes TEXT,
    InitialPrice DECIMAL(10, 2),
    TimeFrame VARCHAR(50),
    Status VARCHAR(20),
    Timestamp TIMESTAMP,
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)
);

CREATE TABLE Bill (
    BillID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT NOT NULL,
    Amount DECIMAL(10, 2),
    Status VARCHAR(20),
    Timestamp TIMESTAMP,
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)
);

CREATE TABLE BillResponse (
    ResponseID INT AUTO_INCREMENT PRIMARY KEY,
    BillID INT NOT NULL,
    ClientNote TEXT,
    DavidNote TEXT,
    Discount DECIMAL(10, 2),
    Status VARCHAR(20),
    Timestamp TIMESTAMP,
    FOREIGN KEY (BillID) REFERENCES Bill(BillID)
);





