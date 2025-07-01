-- Auteurs francophones
INSERT INTO auteur (nom, prenom) VALUES
('Hugo', 'Victor'),
('Dumas', 'Alexandre'), 
('Sand', 'George');

-- Auteurs internationaux (pour les livres mentionnés)
INSERT INTO auteur (nom, prenom) VALUES
('Rowling', 'J.K.'),
('Tolkien', 'J.R.R.'),
('Lewis', 'C.S.');
-- livre 
INSERT INTO livre (titre, date_publication, description) VALUES
('Les Misérables', '1862-01-01', 'Roman historique sur la rédemption'),
('Le Comte de Monte-Cristo', '1844-01-01', 'Aventure et vengeance'),
('La Mare au Diable', '1846-01-01', 'Roman champêtre'),
('Harry Potter et la Pierre Philosophale', '1997-06-26', 'Premier tome de la saga'),
('Le Hobbit', '1937-09-21', 'Prélude au Seigneur des Anneaux'),
('Le Lion, la Sorcière et l''Armoire magique', '1950-10-16', 'Roman fantasy pour enfants');

-- Associations pour les auteurs francophones
INSERT INTO livre_auteur (id_livre, id_auteur) VALUES
(1, 1),  -- Les Misérables -> Victor Hugo
(2, 2),  -- Le Comte de Monte-Cristo -> Alexandre Dumas
(3, 3);  -- La Mare au Diable -> George Sand
-- Associations pour les auteurs internationaux 
INSERT INTO livre_auteur (id_livre, id_auteur) VALUES
(4, 4),  -- Harry Potter -> J.K. Rowling
(5, 5),  -- Le Hobbit -> J.R.R. Tolkien
(6, 6);  -- Le Lion... -> C.S. Lewis



-- Les Misérables (Roman + Histoire)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(1, 1),  -- Roman
(1, 4);  -- Histoire

-- Le Comte de Monte-Cristo (Roman)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(2, 1);  -- Roman

-- La Mare au Diable (Roman)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(3, 1);  -- Roman

-- Harry Potter (Jeunesse + Roman)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(4, 1),  -- Roman
(4, 6);  -- Jeunesse

-- Le Hobbit (Roman + Jeunesse)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(5, 1),  -- Roman
(5, 6);  -- Jeunesse

-- Le Lion, la Sorcière... (Jeunesse + Roman)
INSERT INTO livre_categorie (id_livre, id_categorie) VALUES
(6, 1),  -- Roman
(6, 6);  -- Jeunesse