-- creation de la base 
DROP DATABASE IF EXISTS biblio;
CREATE DATABASE biblio;
USE biblio;
-- table
CREATE TABLE auteur (
    id INT PRIMARY KEY AUTO_INCREMENT ,
    nom VARCHAR(255) , 
    prenom VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE categorie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    type ENUM('livre', 'adherant' ,'admin' , 'pret') NOT NULL,
    description TEXT
) ENGINE=InnoDB;

 
CREATE TABLE statut (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    type ENUM('exemplaire', 'adherant','reservation_prolongement' ,'penalite') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) ,
    prenom VARCHAR(255),
    email VARCHAR(255),
    tel VARCHAR(20),
    adresse VARCHAR(255),
    mdp VARCHAR(255) ,
    date_naissance DATE , 
    id_categorie INT,
    CONSTRAINT fk_categorie FOREIGN KEY (id_categorie) REFERENCES categorie(id)  ON DELETE CASCADE

) ENGINE=InnoDB;

CREATE TABLE histo_statut (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_statut INT , 

    id_exemplaire INT,
    id_utilisateur INT ,
    -- id_pret INT , 
    id_reservation INT ,
    id_penalite INT , 
    id_prolongement INT ,

    date_debut DATE ,
    CONSTRAINT fk_exemplaire_histo_statut FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id)  ON DELETE CASCADE,
    CONSTRAINT fk_utilisateur_histo_statut FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)  ON DELETE CASCADE,
    CONSTRAINT fk_prolongement_histo_statut FOREIGN KEY (id_prolongement) REFERENCES prolongement(id)  ON DELETE CASCADE,
    -- CONSTRAINT fk_pret_histo_statut FOREIGN KEY (id_pret) REFERENCES pret(id)  ON DELETE CASCADE,
    CONSTRAINT fk_reservation_histo_statut FOREIGN KEY (id_reservation) REFERENCES reservation(id)  ON DELETE CASCADE,
    CONSTRAINT fk_penalite_histo_statut FOREIGN KEY (id_penalite) REFERENCES penalite(id)  ON DELETE CASCADE,
    CONSTRAINT fk_statut_histo_statut FOREIGN KEY (id_statut) REFERENCES statut(id)  ON DELETE CASCADE


);

CREATE TABLE livre (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255),
    date_publication DATE , 
    age INT ,
    description TEXT 
) ENGINE=InnoDB;

CREATE TABLE livre_auteur  (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    id_livre INT , 
    id_auteur INT , 
    CONSTRAINT fk_livre_acteur FOREIGN KEY (id_livre) REFERENCES livre(id)  ON DELETE CASCADE,
    CONSTRAINT fk_auteur FOREIGN KEY (id_auteur) REFERENCES auteur(id)  ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE livre_categorie  (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    id_livre INT , 
    id_categorie INT , 
    CONSTRAINT fk_livre_categorie FOREIGN KEY (id_livre) REFERENCES livre(id)  ON DELETE CASCADE,
    CONSTRAINT fk_categorie_livre_categorie FOREIGN KEY (id_categorie) REFERENCES categorie(id)  ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE exemplaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_livre INT , 
    date_acquisition DATE ,
    age_min INT ,
    CONSTRAINT fk_livre_exemplaire FOREIGN KEY (id_livre) REFERENCES livre(id)  ON DELETE CASCADE
) ENGINE=InnoDB;




-- changement
CREATE TABLE abonnement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_adherant INT ,
    date_debut DATE , 
    date_fin DATE , 
    CONSTRAINT fk_adherant_abonement FOREIGN KEY (id_adherant) REFERENCES utilisateur(id)  ON DELETE CASCADE
) ENGINE=InnoDB;



CREATE TABLE pret (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_exemplaire  INT ,
    id_adherant INT , 
    date_debut DATE , 
    date_fin DATE ,
    id_categorie INT , 
    CONSTRAINT fk_exemplaire_pret FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id)  ON DELETE CASCADE,
    CONSTRAINT fk_categorie_pret FOREIGN KEY (id_categorie) REFERENCES categorie(id)  ON DELETE CASCADE,
    CONSTRAINT fk_adherant_pret FOREIGN KEY (id_adherant) REFERENCES utilisateur(id)  ON DELETE CASCADE
) ENGINE=InnoDB;

-- prolongement
CREATE TABLE prolongement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_pret INT NOT NULL,
    last_date_fin DATE NOT NULL,
    new_date_fin DATE NOT NULL,
    created DATE NOT NULL,
    CONSTRAINT fk_pret_prolongement FOREIGN KEY (id_pret) REFERENCES pret(id) ON DELETE CASCADE
);

-- reservation d'un pret
CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_exemplaire INT , 
    id_adherant INT , 
    date_debut DATE ,
    id_categorie_pret INT , 
    date_fin DATE,
    CONSTRAINT fk_categorie_pret_reservation FOREIGN KEY (id_categorie_pret) REFERENCES categorie(id)  ON DELETE CASCADE,
    CONSTRAINT fk_adherant_reservation FOREIGN KEY (id_adherant) REFERENCES utilisateur(id)  ON DELETE CASCADE,
    CONSTRAINT fk_exemplaire_reservation FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id)  ON DELETE CASCADE
) ENGINE=InnoDB;




-- penalite 
CREATE TABLE penalite (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_pret INT , 
    date_debut  DATE,
    date_fin  DATE,
    -- quotas INT ,  -- nbr_livre que l'adherant qu'on va lui en deduire
    -- nbr_jour INT  -- reduction de duree de pret par l'adherant ,
    CONSTRAINT fk_pret_penalite FOREIGN KEY (id_pret) REFERENCES pret(id)  ON DELETE CASCADE
) ENGINE=InnoDB;

-- ferie
CREATE TABLE jour_ferie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jour INT , 
    mois INT , 
    description TEXT
) ENGINE=InnoDB;


CREATE TABLE quota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_categorie_adherant INT ,
    nbr_livre INT , 
    nbr_jour INT,
    CONSTRAINT fk_categorie_adherant_quota FOREIGN KEY (id_categorie_adherant) REFERENCES categorie(id)  ON DELETE CASCADE
);
-- donnee
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


--  Catégories pour les livres (type = 'livre')
INSERT INTO categorie (libelle, type, description) VALUES
('Roman', 'livre', 'Oeuvres de fiction narrative en prose'),
('Science-Fiction', 'livre', 'Genre littéraire imaginant des univers futurs ou alternatifs'),
('Biographie', 'livre', 'Récit de la vie dune personne réelle'),
('Histoire', 'livre', 'Ouvrages traitant dévénements historiques'),
('Informatique', 'livre', 'Livres techniques sur linformatique et la programmation'),
('Jeunesse', 'livre', 'Livres destinés aux enfants et adolescents');

-- Catégories pour les adhérents (type = 'adherant')
INSERT INTO categorie (libelle, type, description) VALUES
('Standard', 'adherant', 'Adhérent classique - 5 livres max, durée 3 semaines'),
('Premium', 'adherant', 'Adhérent privilégié - 10 livres max, durée 4 semaines'),
('Etudiant', 'adherant', 'Tarif réduit pour étudiants - 7 livres max'),
('Senior', 'adherant', 'Tarif réduit pour +65 ans - 5 livres max'),
('Enfant', 'adherant', 'Moins de 12 ans - 3 livres max, durée 4 semaines');

--  Catégories pour les administrateurs (type = 'admin')
INSERT INTO categorie (libelle, type, description) VALUES
('Bibliothécaire', 'admin', 'Accès complet au système'),
('Assistant', 'admin', 'Accès limité aux fonctions courantes'),
('Technique', 'admin', 'Accès technique pour maintenance'),
('Stagiaire', 'admin', 'Accès temporaire limité');

-- Catégories pour les prêts (type = 'pret')
INSERT INTO categorie (libelle, type, description) VALUES
('A domicile', 'pret', 'Prêt classique permettant demporter le livre hors de la bibliothèque'),
('Sur place', 'pret', 'Prêt limité à la consultation dans lenceinte de la bibliothèque');
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

-- Exemplaires pour 'Les Misérables' (livre_id = 1)
INSERT INTO exemplaire (id_livre, date_acquisition , age_min) VALUES
(1, '2020-01-15' , 12),  -- Exemplaire 1
(1, '2020-01-15' , 10),  -- Exemplaire 2
(1, '2021-03-10' , 9);  -- Exemplaire 3

-- Exemplaires pour 'Le Comte de Monte-Cristo' (livre_id = 2)
INSERT INTO exemplaire (id_livre, date_acquisition , age_min) VALUES
(2, '2019-11-05' , 20),
(2, '2022-02-20' , 7);

-- Exemplaires pour 'Harry Potter' (livre_id = 4)
INSERT INTO exemplaire (id_livre, date_acquisition , age_min) VALUES
(4, '2018-06-12', 4),  -- Exemplaire 1
(4, '2020-10-30' , 5),  -- Exemplaire 2
(4, '2020-10-30',14),  -- Exemplaire 3
(4, '2023-01-15' , 12);  -- Exemplaire 4

-- Exemplaire pour 'Le Hobbit' (livre_id = 5)
INSERT INTO exemplaire (id_livre, date_acquisition , age_min) VALUES
(5, '2017-09-22'  ,3);

-- Exemplaires pour 'Le Lion, la Sorcière...' (livre_id = 6)
INSERT INTO exemplaire (id_livre, date_acquisition , age_min) VALUES
(6, '2019-04-18' , 4),
(6, '2021-12-05' , 9);

INSERT INTO quota (id_categorie_adherant , nbr_livre, nbr_jour ) VALUES (7 , 12  , 1);
INSERT INTO quota (id_categorie_adherant , nbr_livre, nbr_jour ) VALUES (8  , 2 , 3);
INSERT INTO quota (id_categorie_adherant , nbr_livre, nbr_jour ) VALUES (9 , 4 , 4);
INSERT INTO quota (id_categorie_adherant , nbr_livre, nbr_jour ) VALUES (10 ,1 , 2);
INSERT INTO quota (id_categorie_adherant , nbr_livre, nbr_jour ) VALUES (11 , 2  , 1);


INSERT INTO jour_ferie (jour, mois, description) VALUES
(1, 1, 'Jour de lAn'),
(29, 3, 'Vendredi Saint'),
(1, 5, 'Fête du Travail'),
(26, 6, 'Indépendance de Madagascar'),
(15, 8, 'Assomption'),
(1, 11, 'Toussaint'),
(25, 12, 'Noël');
