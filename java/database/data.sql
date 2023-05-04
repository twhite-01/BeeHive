BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,family_name) VALUES ('buzzbrigade','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','USER_FAM');
INSERT INTO users (username,password_hash,role,family_name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'ADMIN_FAM');

INSERT INTO request_status (id, status) VALUES (1, 'approved');
INSERT INTO request_status (id, status) VALUES (2, 'pending');
INSERT INTO request_status (id, status) VALUES (3, 'rejected');

INSERT INTO book_format (id, description) VALUES (1, 'Paper');
INSERT INTO book_format (id, description) VALUES (2, 'Digital');
INSERT INTO book_format (id, description) VALUES (3, 'Audio');
INSERT INTO book_format (id, description) VALUES (4, 'Read-Aloud Reader');
INSERT INTO book_format (id, description) VALUES (5, 'Read-Aloud Listener');
INSERT INTO book_format (id, description) VALUES (6, 'Other format');

INSERT INTO members (is_child, user_id, first_name, last_initial, avatar_id, pin)
VALUES (false, 1, 'Mom', 'J', 'pink', '1234');
INSERT INTO members (is_child, user_id, first_name, last_initial, avatar_id)
VALUES (true, 1, 'Chris', 'J', 'blue');
INSERT INTO members (is_child, user_id, first_name, last_initial, avatar_id)
VALUES (true, 1, 'Amber', 'J', 'green');
INSERT INTO members (is_child, user_id, first_name, last_initial, avatar_id)
VALUES (true, 1, 'Trevor', 'J', 'orange');
INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active)
VALUES ('Tickets to the Big Sports Game', 'Two tickets to the Big Sports Game...snacks included!', 500, 1, 1, 1, '2023-04-01', '2023-04-30', true);
INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active)
VALUES ('Tickets to Another Sports Game', 'Another two tickets to another Big Sports Game! This time, its an even bigger one!', 750, 1, 1, 0, '2023-04-01', '2023-04-30', true);
INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active)
VALUES ('Lego Star Wars Millenium Falcon', 'A trip to the Lego store to buy that big fancy set you have been wanting!', 1000, 1, 1, 0, '2023-04-01', '2023-04-30', true);
INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active)
VALUES ('Tickets to Comic-Con NYC 2023', 'A trip to Comic-Con...costume of your choice included!', 1500, 1, 1, 0, '2023-04-01', '2023-04-30', true);
INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active)
VALUES ('Family takes a trip to DisneyLand', 'A family trip to go meet Mickey, to the place where dreams come true!', 80000, 1, 3, 0, '2023-04-01', '2023-04-30', true);


INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781402256240', 'https://pictures.abebooks.com/isbn/9781402256240-us.jpg', 'What Do You Expect? Shes A Teen!', false, 'Arden Greenspan');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9780757302480', 'https://pictures.abebooks.com/isbn/9780757302480-us.jpg', 'Chicken Soup For Every Moms Soul', false, 'Jack Canfield');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781611599879', 'https://pictures.abebooks.com/isbn/9781611599879-us.jpg', 'Mom Knows Best', false, 'Amy Newmark');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781465438195', 'https://pictures.abebooks.com/isbn/9781465438195-us.jpg', 'Lego Star Wars: The Force Awakens', false, 'David Fentiman');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9780060289744', 'https://pictures.abebooks.com/isbn/9780060289744-us.jpg', 'Prairie Girl', false, 'William Anderson');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9780590488181', 'https://pictures.abebooks.com/isbn/9780060000462-us.jpg', 'Little House on the Prairie', false, 'Laura Ingalls Wilder');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9780767913201', 'https://pictures.abebooks.com/isbn/9780767913201-us.jpg', 'Ted Williams: The Biography of an American Hero', false, 'Leigh Montville');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781847324153', 'https://pictures.abebooks.com/isbn/9781847324153-us.jpg', 'How To Sing', false, 'Carrie Grant');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES (' 9780449908396', 'https://pictures.abebooks.com/isbn/9780449908396-us.jpg', 'Dare to Be Yourself', false, 'Alan Cohen');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9780061885198', 'https://pictures.abebooks.com/isbn/9780061885198-us.jpg', 'I Can Read: Superman', false, 'John Sazaklis');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781942021124', 'https://pictures.abebooks.com/isbn/9781942021124-us.jpg', 'Beginners Guide to Drawing Comics', false, 'Christopher Hart');
INSERT INTO book (isbn, cover_url, title, user_created, author)
VALUES ('9781335890009', 'https://pictures.abebooks.com/isbn/9781335890009-us.jpg', 'Christmas on the Ranch: A Clean Romance', false, 'Julianna Morris');


INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,7,10,'Wow, Ted Williams was great at baseball!',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,8,100, 'Singing is my passion',2,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,8,200,'I cant believe how much I learned about singing from this book',2,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,8,90,'I dont know how I will ever choose between singing and sports',2,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,8,15,'Maybe I will like singing a little, I dont know though',2,1,'2023-04-18');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,9,100,'Everyone should read this book',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,9,25,'I am learning a lot about myself now',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (2,9,30,'I feel brand new!',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (1,1,30,'This book understands my situation!',3,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (1,2,50,'Great for the soul!',1,1,'2023-04-18');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (1,3,20, 'I love how there are several moms on the cover',1,1,'2023-04-17');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (3,4,2,'Lego rules!!!',1,1,'2023-04-18');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (3,5,1,'aosijoaifmdao',1,1,'2023-04-17');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (3,6,1,'i definitely read this',3,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (4,10,30,'I liked this one a lot',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (4,11,200,'This was awesome!',1,1,'2023-04-19');
INSERT INTO reading_activity (member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)
VALUES (4,12,150,'Super cool!!!!',1,1,'2023-04-19');
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(1, 1, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(1, 2, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(1, 3, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(2, 7, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(2, 8, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(2, 9, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(3, 4, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(3, 5, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(3, 6, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(4, 10, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(4, 11, false);
INSERT INTO member_book (member_id, book_id, is_completed)
VALUES(4, 12, false);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (2,1,1);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (2,2,2);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (3,3,2);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (4,4,2);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (2,5,2);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (3,5,2);
INSERT INTO member_prize (member_id, prize_id, prize_status_id)
VALUES (4,5,2);



COMMIT TRANSACTION;


