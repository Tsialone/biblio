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
