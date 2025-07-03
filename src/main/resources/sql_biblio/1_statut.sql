-- Statuts pour les exemplaires (type = 'exemplaire')
INSERT INTO statut (libelle, type) VALUES
('Disponible', 'exemplaire'),
('En prêt', 'exemplaire'),
('En réparation', 'exemplaire'),
('Perdu', 'exemplaire'),
('Réservé', 'exemplaire'),
('Hors service', 'exemplaire');

-- Statuts pour les adhérents (type = 'adherant')
INSERT INTO statut (libelle, type) VALUES
('Actif', 'adherant'),
('Inactif', 'adherant'),
('Suspendu', 'adherant'),
('Banni', 'adherant');

-- -- Statuts pour les prêts (type = 'pret')
-- INSERT INTO statut (libelle, type) VALUES
-- ('En cours', 'pret'),
-- ('Retourné', 'pret'),
-- ('En retard', 'pret'),
-- ('Prolongé', 'pret');

-- Statuts pour les réservations (type = 'reservation')
INSERT INTO statut (libelle, type) VALUES
('En attente', 'reservation_prolongement'),
('Confirmée', 'reservation_prolongement'),
('Annulée', 'reservation_prolongement'),
('Expirée', 'reservation_prolongement');


