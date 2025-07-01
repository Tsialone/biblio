-- Exemplaires pour 'Les Misérables' (livre_id = 1)
INSERT INTO exemplaire (id_livre, id_histo_statut, date_acquisition) VALUES
(1, NULL, '2020-01-15'),  -- Exemplaire 1
(1, NULL, '2020-01-15'),  -- Exemplaire 2
(1, NULL, '2021-03-10');  -- Exemplaire 3

-- Exemplaires pour 'Le Comte de Monte-Cristo' (livre_id = 2)
INSERT INTO exemplaire (id_livre, id_histo_statut, date_acquisition) VALUES
(2, NULL, '2019-11-05'),
(2, NULL, '2022-02-20');

-- Exemplaires pour 'Harry Potter' (livre_id = 4)
INSERT INTO exemplaire (id_livre, id_histo_statut, date_acquisition) VALUES
(4, NULL, '2018-06-12'),  -- Exemplaire 1
(4, NULL, '2020-10-30'),  -- Exemplaire 2
(4, NULL, '2020-10-30'),  -- Exemplaire 3
(4, NULL, '2023-01-15');  -- Exemplaire 4

-- Exemplaire pour 'Le Hobbit' (livre_id = 5)
INSERT INTO exemplaire (id_livre, id_histo_statut, date_acquisition) VALUES
(5, NULL, '2017-09-22');

-- Exemplaires pour 'Le Lion, la Sorcière...' (livre_id = 6)
INSERT INTO exemplaire (id_livre, id_histo_statut, date_acquisition) VALUES
(6, NULL, '2019-04-18'),
(6, NULL, '2021-12-05');