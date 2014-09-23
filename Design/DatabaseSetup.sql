CREATE TABLE CustomerDB (
CustomerID SERIAL UNIQUE,
FirstName VARCHAR not null,
LastName VARCHAR not null,
Email VARCHAR not null UNIQUE,
PRIMARY KEY(CustomerID)
);

CREATE TABLE StationDB(
StationNumber SERIAL UNIQUE,
StationName VARCHAR not null
);

CREATE TABLE TravelDB(
TravelNumber BIGSERIAL UNIQUE,
TravelDate DATE,
StartTime TIME,
EndTime TIME,
StartStation INTEGER,
EndStation INTEGER,
Price NUMERIC(4,2),
Zones INTEGER,
CustomerID INTEGER,
PRIMARY KEY(TravelNumber)
);

