-- Inserimento degli autori
INSERT INTO author (id, name, surname, url_of_picture, date_of_birth) VALUES 
(nextval('hibernate_sequence'), 'George', 'Orwell', 'https://i.pinimg.com/originals/fa/84/da/fa84da00eb9cf1f0a1bceba90461b515.jpg', '1903-06-25'),
(nextval('hibernate_sequence'), 'Harper', 'Lee', 'https://www.thefamouspeople.com/profiles/images/harper-lee-5.jpg', '1926-04-28'),
(nextval('hibernate_sequence'), 'Aldous', 'Huxley', 'https://www.thefamouspeople.com/profiles/images/aldous-huxley-2.jpg', '1894-07-26');

-- Inserimento dei libri
INSERT INTO book (id, title, url_image, year) VALUES 
(nextval('hibernate_sequence'), '1984', 'https://www.stagemagazine.org/wp-content/uploads/2017/05/1984-poster.jpg', 1949),
(nextval('hibernate_sequence'), 'To Kill a Mockingbird', 'https://cdn.waterstones.com/bookjackets/large/9781/7847/9781784752637.jpg', 1960),
(nextval('hibernate_sequence'), 'Brave New World', 'https://m.media-amazon.com/images/I/81p56WUuDbL._AC_UF1000,1000_QL80_.jpg', 1932);
