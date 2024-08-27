-- Inserisci autori
INSERT INTO author (date_of_birth, id, name, surname, url_of_picture) VALUES 
('1903-06-25', 1, 'George', 'Orwell', 'https://i.pinimg.com/originals/fa/84/da/fa84da00eb9cf1f0a1bceba90461b515.jpg'),
('1894-07-26', 2, 'Aldous', 'Huxley', 'https://www.thefamouspeople.com/profiles/images/harper-lee-5.jpg'),
('1926-04-28', 3, 'Harper', 'Lee', 'https://www.thefamouspeople.com/profiles/images/aldous-huxley-2.jpg');

-- Inserisci libri
INSERT INTO book (year, id, title, url_image) VALUES 
(1949, 1, '1984', 'https://www.stagemagazine.org/wp-content/uploads/2017/05/1984-poster.jpg'),
(1932, 2, 'Il mondo nuovo / Ritorno al mondo nuovo', 'https://cdn.waterstones.com/bookjackets/large/9781/7847/9781784752637.jpg'),
(1960, 3, 'Il buio oltre la siepe', 'https://m.media-amazon.com/images/I/81p56WUuDbL._AC_UF1000,1000_QL80_.jpg');

INSERT INTO book_author (book_id, author_id) VALUES
(1, 1), -- "1984" scritto da George Orwell
(2, 2), -- "Il buio oltre la siepe" scritto da Harper Lee
(3, 3); -- "Il mondo nuovo / Ritorno al mondo nuovo" scritto da Aldous Huxley
