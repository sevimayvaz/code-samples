CREATE TABLE IF NOT EXISTS Product (
	product_id serial,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(300) NOT NULL,
	price NUMERIC(8,2) NOT NULL,
	PRIMARY KEY	(product_id)
);

CREATE TABLE IF NOT EXISTS Users (
	username VARCHAR(25) UNIQUE NOT NULL,
	password VARCHAR(200) NOT NULL,
	enabled BOOL NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS UserRoles (
	username VARCHAR(25) UNIQUE NOT NULL,
	role VARCHAR(50) NOT NULL,
	PRIMARY KEY (username,role),
	FOREIGN KEY (username) REFERENCES Users (username) 
);