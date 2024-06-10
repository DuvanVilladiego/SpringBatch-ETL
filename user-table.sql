create table Users (
	id SERIAL PRIMARY KEY,
	name varchar(20) not null,
	email varchar(60) not null
);