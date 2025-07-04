-- nbr emprun par livre
CREATE OR REPLACE VIEW v_exemplaire_emprunt AS
SELECT 
    e.id AS id_exemplaire,
    COUNT(p.id) AS nbr_emprunt
FROM exemplaire e
LEFT JOIN pret p ON p.id_exemplaire = e.id
GROUP BY e.id;


-- nombre d'adherant par categorie
CREATE OR REPLACE VIEW v_adherants_par_categorie AS
SELECT 
    c.libelle AS categorie,
    COUNT(u.id) AS total_adherants
FROM utilisateur u
JOIN categorie c ON c.id = u.id_categorie
WHERE c.type = 'adherant'
GROUP BY c.libelle;

-- nombre de pret par jour
CREATE OR REPLACE VIEW v_pret_par_jour AS
SELECT 
    date_debut,
    COUNT(*) AS total_prets
FROM pret
GROUP BY date_debut
ORDER BY date_debut DESC;

-- livre les plus emprente
CREATE OR REPLACE VIEW v_top_livres_empruntes AS
SELECT 
    l.titre,
    COUNT(p.id) AS total_emprunts
FROM livre l
JOIN exemplaire e ON l.id = e.id_livre
JOIN pret p ON p.id_exemplaire = e.id
GROUP BY l.id, l.titre
ORDER BY total_emprunts DESC
LIMIT 5;
-- abonnements en cours
CREATE OR REPLACE VIEW v_abonnements_en_cours AS
SELECT 
    u.nom,
    u.prenom,
    a.date_debut,
    a.date_fin
FROM abonnement a
JOIN utilisateur u ON u.id = a.id_adherant
WHERE CURDATE() BETWEEN a.date_debut AND a.date_fin;

-- penalite en cours
CREATE OR REPLACE VIEW v_penalites_en_cours AS
SELECT 
    u.nom,
    u.prenom,
    p.date_debut,
    p.date_fin
FROM penalite p
JOIN pret pr ON pr.id = p.id_pret
JOIN utilisateur u ON u.id = pr.id_adherant
WHERE CURDATE() BETWEEN p.date_debut AND p.date_fin;

-- reservation active
CREATE OR REPLACE VIEW v_reservations_actives AS
SELECT 
    u.nom,
    u.prenom,
    l.titre,
    r.date_debut,
    r.date_fin
FROM reservation r
JOIN exemplaire e ON e.id = r.id_exemplaire
JOIN livre l ON l.id = e.id_livre
JOIN utilisateur u ON u.id = r.id_adherant
WHERE CURDATE() BETWEEN r.date_debut AND r.date_fin;

-- quota
CREATE OR REPLACE VIEW v_quota_categorie AS
SELECT 
    c.libelle AS categorie,
    q.nbr_livre,
    q.nbr_jour
FROM quota q
JOIN categorie c ON c.id = q.id_categorie_adherant;
