
CREATE TABLE unite (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE,
  abreviation VARCHAR(10) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE type_mouvement (
  id INT AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE race_poule (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE,
  duree_vie_moyenne INT NOT NULL,
  age_ponte_debut INT NOT NULL,
  ponte_par_jour DECIMAL(3,2)
) ENGINE=InnoDB;

CREATE TABLE sexe (
  id INT AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(50) NOT NULL UNIQUE,
  abreviation VARCHAR(10) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE departement (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE role (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description TEXT NOT NULL,
  id_departement INT,
  CONSTRAINT fk_role_departement FOREIGN KEY (id_departement) REFERENCES departement(id)
) ENGINE=InnoDB;

CREATE TABLE frequence (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description TEXT NOT NULL,
  nb_jours INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE etat_oeuf (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description TEXT NOT NULL
) ENGINE=InnoDB;

-- ===== GESTION DES EMPLOYÉS =====

CREATE TABLE employe (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  dtn DATE NOT NULL,
  id_sexe INT,
  email VARCHAR(100) NOT NULL UNIQUE,
  mdp VARCHAR(255) NOT NULL,
  date_embauche DATE NOT NULL,
  date_renvoie DATE DEFAULT NULL,
  id_role INT,
  actif BOOLEAN DEFAULT 1,
  CONSTRAINT fk_employe_sexe FOREIGN KEY (id_sexe) REFERENCES sexe(id),
  CONSTRAINT fk_employe_role FOREIGN KEY (id_role) REFERENCES role(id)
) ENGINE=InnoDB;

CREATE TABLE salaire (
  id INT AUTO_INCREMENT PRIMARY KEY,
  montant DECIMAL(10,2) NOT NULL,
  date_debut DATE NOT NULL,
  date_fin DATE DEFAULT NULL,
  id_employe INT,
  id_frequence INT,
  CONSTRAINT fk_salaire_employe FOREIGN KEY (id_employe) REFERENCES employe(id),
  CONSTRAINT fk_salaire_frequence FOREIGN KEY (id_frequence) REFERENCES frequence(id)
) ENGINE=InnoDB;

CREATE TABLE payment_salaire (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_employe INT,
  id_salaire INT,
  date_payment DATE NOT NULL,
  montant_paye DECIMAL(10,2) NOT NULL,
  periode_debut DATE NOT NULL,
  periode_fin DATE NOT NULL,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_payment_employe FOREIGN KEY (id_employe) REFERENCES employe(id),
  CONSTRAINT fk_payment_salaire FOREIGN KEY (id_salaire) REFERENCES salaire(id)
) ENGINE=InnoDB;

-- ===== GESTION DES CAGES ET PROMOTIONS =====

CREATE TABLE cage (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  description TEXT,
  capacite INT NOT NULL,
  actif BOOLEAN DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE promotion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  age_arrivee INT NOT NULL DEFAULT 0,
  nombre_initial INT NOT NULL,
  nombre_actuel INT NOT NULL,
  id_race_poule INT,
  poids_moyen DECIMAL(5,2),
  prix_achat_unitaire DECIMAL(10,2),
  date_arrivee DATE NOT NULL,
  date_reforme DATE DEFAULT NULL,
  id_employe_responsable INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_promotion_race FOREIGN KEY (id_race_poule) REFERENCES race_poule(id),
  CONSTRAINT fk_promotion_employe FOREIGN KEY (id_employe_responsable) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE mouvement_cage (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_cage INT,
  id_promotion INT,
  id_type_mouvement INT,
  nombre_poule INT NOT NULL,
  date_mouvement DATE NOT NULL,
  id_employe INT,
  observation TEXT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_mouvement_cage_cage FOREIGN KEY (id_cage) REFERENCES cage(id),
  CONSTRAINT fk_mouvement_cage_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id),
  CONSTRAINT fk_mouvement_cage_type_mouvement FOREIGN KEY (id_type_mouvement) REFERENCES type_mouvement(id),
  CONSTRAINT fk_mouvement_cage_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE etat_cage (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_cage INT,
  date_etat DATE NOT NULL,
  description TEXT NOT NULL,
  id_employe INT,
  CONSTRAINT fk_etat_cage_cage FOREIGN KEY (id_cage) REFERENCES cage(id),
  CONSTRAINT fk_etat_cage_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE promotion_mort (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_promotion INT,
  nb_poule INT NOT NULL,
  date_mort DATE NOT NULL,
  cause_mort TEXT,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_promotion_mort_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id),
  CONSTRAINT fk_promotion_mort_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

-- ===== GESTION DES PROVENDES =====

CREATE TABLE provende (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE,
  id_unite INT,
  stock_actuel DECIMAL(10,2) DEFAULT 0,
  stock_minimum DECIMAL(10,2) DEFAULT 0,
  actif BOOLEAN DEFAULT 1,
  CONSTRAINT fk_provende_unite FOREIGN KEY (id_unite) REFERENCES unite(id)
) ENGINE=InnoDB;

CREATE TABLE prix_provende (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_provende INT,
  montant DECIMAL(10,2) NOT NULL,
  date_prix DATE NOT NULL,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_prix_provende_provende FOREIGN KEY (id_provende) REFERENCES provende(id),
  CONSTRAINT fk_prix_provende_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE mouvement_provende (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_provende INT,
  id_type_mouvement INT,
  date_mouvement DATE NOT NULL,
  quantite DECIMAL(10,2) NOT NULL,
  prix_unitaire DECIMAL(10,2) DEFAULT NULL,
  id_employe INT,
  observation TEXT,
  validation BOOLEAN DEFAULT 0,
  id_promotion INT DEFAULT NULL,
  quantite_par_poule DECIMAL(10,2) DEFAULT NULL,
  CONSTRAINT fk_mouvement_provende_provende FOREIGN KEY (id_provende) REFERENCES provende(id),
  CONSTRAINT fk_mouvement_provende_type_mouvement FOREIGN KEY (id_type_mouvement) REFERENCES type_mouvement(id),
  CONSTRAINT fk_mouvement_provende_employe FOREIGN KEY (id_employe) REFERENCES employe(id),
  CONSTRAINT fk_mouvement_provende_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id)
) ENGINE=InnoDB;

CREATE TABLE provende_race (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_provende INT,
  id_race_poule INT,
  age_debut INT NOT NULL,
  age_fin INT NOT NULL,
  quantite_recommandee DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_provende_race_provende FOREIGN KEY (id_provende) REFERENCES provende(id),
  CONSTRAINT fk_provende_race_race FOREIGN KEY (id_race_poule) REFERENCES race_poule(id)
) ENGINE=InnoDB;

CREATE TABLE alimentation_promotion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_promotion INT,
  id_provende INT,
  date_alimentation DATE NOT NULL,
  quantite_donnee DECIMAL(10,2) NOT NULL,
  quantite_par_poule DECIMAL(10,2) NOT NULL,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_alimentation_promotion_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id),
  CONSTRAINT fk_alimentation_promotion_provende FOREIGN KEY (id_provende) REFERENCES provende(id),
  CONSTRAINT fk_alimentation_promotion_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

-- ===== GESTION DES MÉDICAMENTS =====

CREATE TABLE medicament (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE,
  id_unite INT,
  stock_actuel DECIMAL(10,2) DEFAULT 0,
  stock_minimum DECIMAL(10,2) DEFAULT 0,
  date_expiration DATE DEFAULT NULL,
  actif BOOLEAN DEFAULT 1,
  CONSTRAINT fk_medicament_unite FOREIGN KEY (id_unite) REFERENCES unite(id)
) ENGINE=InnoDB;

CREATE TABLE prix_medicament (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_medicament INT,
  montant DECIMAL(10,2) NOT NULL,
  date_prix DATE NOT NULL,
  quantite_achetee DECIMAL(10,2) NOT NULL,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_prix_medicament_medicament FOREIGN KEY (id_medicament) REFERENCES medicament(id),
  CONSTRAINT fk_prix_medicament_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE mouvement_medicament (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_medicament INT,
  id_type_mouvement INT,
  date_mouvement DATE NOT NULL,
  quantite DECIMAL(10,2) NOT NULL,
  prix_unitaire DECIMAL(10,2) DEFAULT NULL,
  id_employe INT,
  id_promotion INT DEFAULT NULL,
  description TEXT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_mouvement_medicament_medicament FOREIGN KEY (id_medicament) REFERENCES medicament(id),
  CONSTRAINT fk_mouvement_medicament_type_mouvement FOREIGN KEY (id_type_mouvement) REFERENCES type_mouvement(id),
  CONSTRAINT fk_mouvement_medicament_employe FOREIGN KEY (id_employe) REFERENCES employe(id),
  CONSTRAINT fk_mouvement_medicament_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id)
) ENGINE=InnoDB;

CREATE TABLE medicament_race (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_medicament INT,
  id_race_poule INT,
  id_frequence INT,
  age_debut INT NOT NULL,
  age_fin INT DEFAULT NULL,
  quantite_par_poule DECIMAL(10,2) NOT NULL,
  obligatoire BOOLEAN DEFAULT 0,
  CONSTRAINT fk_medicament_race_medicament FOREIGN KEY (id_medicament) REFERENCES medicament(id),
  CONSTRAINT fk_medicament_race_race FOREIGN KEY (id_race_poule) REFERENCES race_poule(id),
  CONSTRAINT fk_medicament_race_frequence FOREIGN KEY (id_frequence) REFERENCES frequence(id)
) ENGINE=InnoDB;

-- ===== GESTION DES ŒUFS =====

CREATE TABLE recolte (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_promotion INT,
  id_cage INT,
  id_etat_oeuf INT,
  nombre_oeuf INT NOT NULL,
  date_recolte DATE NOT NULL,
  id_employe INT,
  observation TEXT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_recolte_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id),
  CONSTRAINT fk_recolte_cage FOREIGN KEY (id_cage) REFERENCES cage(id),
  CONSTRAINT fk_recolte_etat_oeuf FOREIGN KEY (id_etat_oeuf) REFERENCES etat_oeuf(id),
  CONSTRAINT fk_recolte_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

CREATE TABLE prix_oeuf (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_etat_oeuf INT,
  montant DECIMAL(10,2) NOT NULL,
  date_prix DATE NOT NULL,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_prix_oeuf_etat FOREIGN KEY (id_etat_oeuf) REFERENCES etat_oeuf(id),
  CONSTRAINT fk_prix_oeuf_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

-- ===== GESTION DES VENTES =====

CREATE TABLE client (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  telephone VARCHAR(20),
  email VARCHAR(100),
  adresse TEXT,
  actif BOOLEAN DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE type_vente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  produit VARCHAR(100) NOT NULL,
  unite VARCHAR(50) NOT NULL,
  prix_unitaire DECIMAL(10,2) NOT NULL,
  date_prix DATE NOT NULL,
  actif BOOLEAN DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE vente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_client INT,
  id_type_vente INT,
  quantite DECIMAL(10,2) NOT NULL,
  prix_unitaire DECIMAL(10,2) NOT NULL,
  montant_total DECIMAL(10,2) NOT NULL,
  date_vente DATE NOT NULL,
  id_employe INT,
  paye BOOLEAN DEFAULT 0,
  date_payment DATE DEFAULT NULL,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_vente_client FOREIGN KEY (id_client) REFERENCES client(id),
  CONSTRAINT fk_vente_type_vente FOREIGN KEY (id_type_vente) REFERENCES type_vente(id),
  CONSTRAINT fk_vente_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

-- ===== GESTION FINANCIÈRE =====

CREATE TABLE depense (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description TEXT NOT NULL,
  montant DECIMAL(10,2) NOT NULL,
  date_depense DATE NOT NULL,
  categorie VARCHAR(50) NOT NULL,
  id_employe INT,
  validation BOOLEAN DEFAULT 0,
  CONSTRAINT fk_depense_employe FOREIGN KEY (id_employe) REFERENCES employe(id)
) ENGINE=InnoDB;

-- ===== SUIVI EMPLOYÉ-POULES =====

CREATE TABLE nb_poule_par_employe (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_employe INT,
  id_promotion INT,
  nb_poule_responsable INT NOT NULL,
  date_debut DATE NOT NULL,
  date_fin DATE DEFAULT NULL,
  actif BOOLEAN DEFAULT 1,
  CONSTRAINT fk_nb_poule_employe FOREIGN KEY (id_employe) REFERENCES employe(id),
  CONSTRAINT fk_nb_poule_promotion FOREIGN KEY (id_promotion) REFERENCES promotion(id)
) ENGINE=InnoDB;
