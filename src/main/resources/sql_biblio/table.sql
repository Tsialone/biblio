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
    type ENUM('exemplaire', 'adherant' , 'pret' ,'reservation' ,'penalite') NOT NULL
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
    id_pret INT , 
    id_reservation INT ,
    id_penalite INT , 

    date_debut DATE ,
    CONSTRAINT fk_exemplaire_histo_statut FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id)  ON DELETE CASCADE,
    CONSTRAINT fk_utilisateur_histo_statut FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)  ON DELETE CASCADE,
    CONSTRAINT fk_pret_histo_statut FOREIGN KEY (id_pret) REFERENCES pret(id)  ON DELETE CASCADE,
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
    CONSTRAINT fk_livre_exemplaire FOREIGN KEY (id_livre) REFERENCES livre(id)  ON DELETE CASCADE
) ENGINE=InnoDB;




-- changement
CREATE TABLE abonnement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_adherant INT ,
    date_debut DATE , 
    date_fin DATE , 
    CONSTRAINT fk_categorie_adherant_abonement FOREIGN KEY (id_adherant) REFERENCES utilisateur(id)  ON DELETE CASCADE
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

-- reservation d'un pret
CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_pret INT , 
    date_demande DATE ,
    CONSTRAINT fk_pret FOREIGN KEY (id_pret) REFERENCES pret(id)  ON DELETE CASCADE
) ENGINE=InnoDB;

-- penalite 
CREATE TABLE penalite (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_pret INT , 
    date_debut  DATE,
    quotas INT ,  -- nbr_livre que l'adherant qu'on va lui en deduire
    nbr_jour INT , -- reduction de duree de pret par l'adherant ,
    CONSTRAINT fk_pret_penalite FOREIGN KEY (id_pret) REFERENCES pret(id)  ON DELETE CASCADE
) ENGINE=InnoDB;

-- ferie
CREATE TABLE jour_ferie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE ,
    description TEXT
) ENGINE=InnoDB;


CREATE TABLE quota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_categorie_adherant INT ,
    id_categorie_abonnement INT ,
    nbr_livre INT , 
    nbr_jour INT,
    CONSTRAINT fk_categorie_adherant_quota FOREIGN KEY (id_categorie_adherant) REFERENCES categorie(id)  ON DELETE CASCADE,
    CONSTRAINT fk_categorie_abonnement_quota FOREIGN KEY (id_categorie_abonnement) REFERENCES categorie(id)  ON DELETE CASCADE
);