BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, book_format, book, reading_activity, members, prize, member_prize, request_status, avatar;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	family_name varchar(50),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE book_format (
	id SERIAL, 
	description varchar(20) NOT NULL, 
	CONSTRAINT PK_book_format PRIMARY KEY (id)
);

CREATE TABLE book (
	id SERIAL, 
	isbn varchar(100), 
	cover_url varchar, 
	title varchar(400) NOT NULL,
	description varchar(5000),
	user_created boolean NOT NULL DEFAULT FALSE, 
	author varchar(50) NOT NULL,
	CONSTRAINT PK_book PRIMARY KEY (id)
);

CREATE TABLE reading_activity(
	id SERIAL, 
	member_id int NOT NULL, 
	book_id int NOT NULL, 
	minutes_read int NOT NULL, 
	notes varchar(500), 
	format_id int NOT NULL, 
	request_status_id int NOT NULL,
	date_read date NOT NULL,
	CONSTRAINT PK_reading_activity PRIMARY KEY (id)
);

CREATE TABLE members(
	id SERIAL, 
	is_child boolean NOT NULL DEFAULT FALSE,
	user_id int NOT NULL, 
	first_name varchar(40) NOT NULL, 
	last_initial varchar(3) NOT NULL, 
	avatar_id varchar(100) NOT NULL, 
	pin varchar(4), 
	CONSTRAINT PK_member PRIMARY KEY (id)
);

CREATE TABLE prize (
	id SERIAL, 
	prize_name varchar(64) NOT NULL, 
	description varchar(500),
	goal_minutes int NOT NULL, 
	user_id int, 
	max_winners int NOT NULL DEFAULT 1, 
	current_winners int NOT NULL DEFAULT 0,
	start_date date, 
	end_date date, 
	is_active boolean NOT NULL DEFAULT TRUE,
	CONSTRAINT PK_prize PRIMARY KEY (id)
);

CREATE TABLE member_prize(
	member_id int NOT NULL,
	prize_id int NOT NULL,
	prize_status_id int NOT NULL,
	CONSTRAINT PK_member_prize PRIMARY KEY (member_id, prize_id)
);

CREATE TABLE request_status(
	id int NOT NULL, 
	status varchar(12) NOT NULL, 
	CONSTRAINT PK_request_status PRIMARY KEY (id)
);

CREATE TABLE member_book(
	member_id int NOT NULL,
	book_id int NOT NULL,
	is_completed boolean DEFAULT false,
	CONSTRAINT PK_book_member PRIMARY KEY (member_id, book_id )
);


ALTER TABLE reading_activity ADD FOREIGN KEY (member_id) REFERENCES members (id);
ALTER TABLE reading_activity ADD FOREIGN KEY (book_id) REFERENCES book (id);
ALTER TABLE reading_activity ADD FOREIGN KEY (format_id) REFERENCES book_format (id);
ALTER TABLE reading_activity ADD FOREIGN KEY (request_status_id) REFERENCES request_status (id);

ALTER TABLE members ADD FOREIGN KEY (user_id) REFERENCES users (user_id);


ALTER TABLE member_prize ADD FOREIGN KEY (member_id) REFERENCES members (id);
ALTER TABLE member_prize ADD FOREIGN KEY (prize_id) REFERENCES prize (id);
ALTER TABLE member_prize ADD FOREIGN KEY (prize_status_id) REFERENCES request_status (id);


ALTER TABLE prize ADD FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE member_book ADD FOREIGN KEY (member_id) REFERENCES members (id);
ALTER TABLE member_book ADD FOREIGN KEY (book_id) REFERENCES book (id);








COMMIT TRANSACTION;

