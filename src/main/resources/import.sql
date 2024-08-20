INSERT INTO author (id, name, surname, date_of_birth, url_of_picture) VALUES
(nextval('hibernate_sequence'), 'George', 'Orwell', '1903-06-25', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/George_Orwell_press_photo.jpg/440px-George_Orwell_press_photo.jpg'),
(nextval('hibernate_sequence'), 'Harper', 'Lee', '1926-04-28', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Harper_Lee_1961.jpg/440px-Harper_Lee_1961.jpg'),
(nextval('hibernate_sequence'), 'Aldous', 'Huxley', '1894-07-26', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Aldous_Huxley.jpg/440px-Aldous_Huxley.jpg'),
(nextval('hibernate_sequence'), 'F. Scott', 'Fitzgerald', '1896-09-24', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/F_Scott_Fitzgerald_1921.jpg/440px-F_Scott_Fitzgerald_1921.jpg'),
(nextval('hibernate_sequence'), 'Herman', 'Melville', '1819-08-01', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Herman_Melville.jpg/440px-Herman_Melville.jpg'),
(nextval('hibernate_sequence'), 'J.D.', 'Salinger', '1919-01-01', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/J._D._Salinger.jpg/440px-J._D._Salinger.jpg');

INSERT INTO book (id, title, year) VALUES
(nextval('hibernate_sequence'), '1984', 1949),
(nextval('hibernate_sequence'), 'To Kill a Mockingbird', 1960),
(nextval('hibernate_sequence'), 'Brave New World', 1932),
(nextval('hibernate_sequence'), 'The Great Gatsby', 1925),
(nextval('hibernate_sequence'), 'Moby Dick', 1851),
(nextval('hibernate_sequence'), 'The Catcher in the Rye', 1951);
