DROP ALL OBJECTS;

CREATE TABLE Client
(
    Client_id int NOT NULL auto_increment,
    Name      varchar(50) NOT NULL,
    CONSTRAINT PK_client PRIMARY KEY ( Client_id )
);

CREATE TABLE Account
(
    Account_id     int NOT NULL auto_increment,
    Client_id      int NOT NULL,
    Account_number varchar(50) unique NOT NULL,
    Balance        real NOT NULL,
    CONSTRAINT PK_account PRIMARY KEY ( Account_id, Client_id ),
    CONSTRAINT FK_16 FOREIGN KEY ( Client_id ) REFERENCES Client ( Client_id )
);

CREATE INDEX fkIdx_16 ON Account
    (
     Client_id
        );

CREATE TABLE Card
(
    Card_id     int NOT NULL auto_increment,
    Account_id  int NOT NULL,
    Client_id   int NOT NULL,
    Card_number varchar(50) unique NOT NULL,
    CONSTRAINT PK_cards PRIMARY KEY ( Card_id, Account_id, Client_id ),
    CONSTRAINT FK_19 FOREIGN KEY ( Account_id, Client_id ) REFERENCES Account ( Account_id, Client_id )
);

CREATE INDEX fkIdx_19 ON Card
    (
     Account_id,
     Client_id
        );
