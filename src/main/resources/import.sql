insert into book (id, title, year) values
(nextval('hibernate_sequence'), '1984', 1949),
(nextval('hibernate_sequence'), 'to kill a mockingbird', 1960),
(nextval('hibernate_sequence'), 'brave new world', 1932),
(nextval('hibernate_sequence'), 'the great gatsby', 1925),
(nextval('hibernate_sequence'), 'moby dick', 1851),
(nextval('hibernate_sequence'), 'the catcher in the rye', 1951);

insert into author (id, name, surname, date_of_birth, url_of_picture) values
(nextval('hibernate_sequence'), 'george', 'orwell', '1903-06-25', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/george_orwell_press_photo.jpg/440px-george_orwell_press_photo.jpg'),
(nextval('hibernate_sequence'), 'harper', 'lee', '1926-04-28', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/harper_lee_1961.jpg/440px-harper_lee_1961.jpg'),
(nextval('hibernate_sequence'), 'aldous', 'huxley', '1894-07-26', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/aldous_huxley.jpg/440px-aldous_huxley.jpg'),
(nextval('hibernate_sequence'), 'f. scott', 'fitzgerald', '1896-09-24', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/f_scott_fitzgerald_1921.jpg/440px-f_scott_fitzgerald_1921.jpg'),
(nextval('hibernate_sequence'), 'herman', 'melville', '1819-08-01', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/herman_melville.jpg/440px-herman_melville.jpg'),
(nextval('hibernate_sequence'), 'j.d.', 'salinger', '1919-01-01', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/j._d._salinger.jpg/440px-j._d._salinger.jpg');