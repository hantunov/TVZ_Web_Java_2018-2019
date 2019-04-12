CREATE TABLE IF NOT EXISTS users (
	id IDENTITY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	enabled BIT NOT NULL);
	
CREATE TABLE IF NOT EXISTS authorities (
	username VARCHAR(20) NOT NULL,
	authority VARCHAR(20) NOT NULL);
	
CREATE TABLE IF NOT EXISTS novcanici (
	id IDENTITY,
	naziv VARCHAR(100),
	datum DATE,
	userID INT,
	FOREIGN KEY (userID) REFERENCES users(id));

CREATE TABLE IF NOT EXISTS troskovi (
	id IDENTITY,
	naziv VARCHAR(100),
	iznos DECIMAL(6,2),
	vrsta VARCHAR(100),
	datum DATE,
	novcanikID INT,
	FOREIGN KEY (novcanikID) REFERENCES novcanici(id));