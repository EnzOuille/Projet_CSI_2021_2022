CREATE TABLE MotsClefs
(
    mtc_id  SERIAL PRIMARY KEY
    ,
    mtc_nom VARCHAR(100)
);

CREATE TABLE Domaine
(
    dom_id   SERIAL PRIMARY KEY ,
    dom_nom  VARCHAR(100) UNIQUE,
    dom_etat VARCHAR(100) NOT NULL DEFAULT 'non_validee'
);

CREATE TABLE Abonne
(
    abn_id            SERIAL PRIMARY KEY,
    abn_nom           VARCHAR(100) NOT NULL,
    abn_prenom        VARCHAR(100) NOT NULL,
    abn_email         VARCHAR(255) NOT NULL,
    abn_pseudo        VARCHAR(100) NOT NULL,
    abn_mdp           VARCHAR(100) NOT NULL,
    abn_admin          boolean      not null,
    abn_date_inscrit  DATE         NOT NULL DEFAULT CURRENT_DATE,
    abn_nb_news       INT          NOT NULL DEFAULT 0,
    abn_nb_news_valid INT          NOT NULL DEFAULT 0,
    abn_conf          boolean               DEFAULT FALSE
);

CREATE TABLE ObjetEvalue
(
    obe_id SERIAL PRIMARY KEY
);

CREATE TABLE News
(
    new_id            int not null PRIMARY KEY,
    new_texte         VARCHAR(300) NOT NULL,
    new_date_creation DATE         NOT NULL DEFAULT CURRENT_DATE,
    new_etat          VARCHAR(100)          DEFAULT 'non_validee',
    new_abn_id        int NOT NULL,
    new_mtc_1         INT NOT NULL,
    new_mtc_2         INT NOT NULL,
    new_mtc_3         INT NOT NULL,
    new_dom_id        INT NOT NULL,
    CONSTRAINT fk_news_id FOREIGN KEY (new_id) REFERENCES ObjetEvalue (obe_id),
    CONSTRAINT fk_news_abn FOREIGN KEY (new_abn_id) REFERENCES Abonne (abn_id),
    CONSTRAINT fk_news_mtc1 FOREIGN KEY (new_mtc_1) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_news_mtc2 FOREIGN KEY (new_mtc_2) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_news_mtc3 FOREIGN KEY (new_mtc_3) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_news_dom FOREIGN KEY (new_dom_id) REFERENCES Domaine (dom_id)
);

CREATE TABLE ArchivageNews
(
    arc_id             int not null PRIMARY KEY,
    arc_texte          VARCHAR(300) NOT NULL,
    arc_date_archivage DATE         NOT NULL DEFAULT CURRENT_DATE,
    arc_etat           VARCHAR(100) NOT NULL,
    arc_abn_id         int NOT NULL,
    arc_mtc_1         INT NOT NULL,
    arc_mtc_2         INT NOT NULL,
    arc_mtc_3         INT NOT NULL,
    arc_dom_id         INT NOT NULL,
    CONSTRAINT fk_news_id FOREIGN KEY (arc_id) REFERENCES ObjetEvalue (obe_id),
    CONSTRAINT fk_archivagenews_abn FOREIGN KEY (arc_abn_id) REFERENCES Abonne (abn_id),
    CONSTRAINT fk_archivagenews_mtc1 FOREIGN KEY (arc_mtc_1) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_archivagenews_mtc2 FOREIGN KEY (arc_mtc_2) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_archivagenews_mtc3 FOREIGN KEY (arc_mtc_3) REFERENCES MotsClefs (mtc_id),
    CONSTRAINT fk_archivagenews_dom FOREIGN KEY (arc_dom_id) REFERENCES Domaine (dom_id)
);

CREATE TABLE AEvalue
(
    eval_id                SERIAL PRIMARY KEY,
    eval_abn_id          int NOT NULL,
    eval_objet int NOT NULL,
    eval_justification     VARCHAR(300) DEFAULT NULL,
    eval_date_justifcation date         DEFAULT NULL,
    CONSTRAINT fk_aevalue_evaluateur FOREIGN KEY (eval_abn_id) REFERENCES Abonne(abn_id),
    CONSTRAINT fk_aevalue_objet FOREIGN KEY (eval_objet) REFERENCES ObjetEvalue(obe_id)
);

CREATE TABLE Notification
(
    ntf_id      SERIAL PRIMARY KEY,
    ntf_abn_id  int NOT NULL,
    ntf_contenu VARCHAR(300) NOT NULL,
    CONSTRAINT fk_notification_abn FOREIGN KEY (ntf_abn_id) REFERENCES Abonne (abn_id)
);

CREATE TABLE DomainePrivilegie
(
    dpl_abn_id          int NOT NULL,
    dpl_dom_id          int NOT NULL,
    CONSTRAINT fk_domaineprivilegie_abonne FOREIGN KEY (dpl_abn_id) REFERENCES Abonne(abn_id),
    CONSTRAINT fk_domaine_abonne FOREIGN KEY (dpl_dom_id) REFERENCES Domaine(dom_id)
);

INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (13, 'Melisenda', 'Janek', 'mjanekc@washington.edu', 'mjanekc', 'y51WADh3eF', false, '2021-08-04', 87, 25, true);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (16, 'Loralyn', 'McCrow', 'lmccrowf@scientificamerican.com', 'lmccrowf', 'HoMhYs', false, '2021-06-18', 95, 86, true);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (18, 'Rosetta', 'Dolby', 'rdolbyh@guardian.co.uk', 'rdolbyh', '8BAtuD36', false, '2021-09-20', 48, 72, true);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (1, 'Lori', 'Bacchus', 'lbacchus0@google.com.au', 'lbacchus0', 'TFoR7msPOOA', true, '2021-07-13', 97, 89, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (15, 'Elwood', 'Purtell', 'epurtelle@networkadvertising.org', 'epurtelle', 'IpWqkv9RDHTv', true, '2021-03-14', 66, 75, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (3, 'Terence', 'Walter', 'twalter2@bluehost.com', 'twalter2', 'uZZ0IDI2nt', true, '2021-06-07', 89, 19, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (17, 'Joell', 'Couve', 'jcouveg@dailymail.co.uk', 'jcouveg', 'P7OZFk', false, '2021-03-11', 99, 32, true);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (2, 'Consalve', 'Sprouls', 'csprouls1@wikipedia.org', 'csprouls1', 'Q4uZixk', true, '2021-08-16', 62, 40, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (14, 'Rozalie', 'Tomaszewski', 'rtomaszewskid@ucoz.com', 'rtomaszewskid', 'JX6m8G', false, '2021-11-15', 26, 29, true);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (4, 'Kinnie', 'Evreux', 'kevreux3@skyrock.com', 'kevreux3', 'eomIUEEMFB', false, '2021-07-07', 34, 50, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (5, 'Cristobal', 'Reddyhoff', 'creddyhoff4@berkeley.edu', 'creddyhoff4', 'QAdpxIXryQ', false, '2021-12-08', 63, 40, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (6, 'Dorene', 'Feirn', 'dfeirn5@goo.gl', 'dfeirn5', 'nNMCd5iM', false, '2021-02-17', 35, 25, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (7, 'Augusto', 'Brugger', 'abrugger6@businessinsider.com', 'abrugger6', 'HTSpgsP', false, '2021-06-12', 89, 2, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (8, 'Port', 'Cammock', 'pcammock7@zimbio.com', 'pcammock7', 'VfJTLsGDeI', false, '2021-12-03', 89, 26, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (9, 'Scottie', 'Gulk', 'sgulk8@time.com', 'sgulk8', '3ZRxYLdJvV', false, '2021-09-01', 52, 71, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (10, 'Bessy', 'Kermode', 'bkermode9@cocolog-nifty.com', 'bkermode9', '56KVNoWKT0VC', false, '2021-05-07', 10, 54, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (11, 'Jacynth', 'Tucsell', 'jtucsella@japanpost.jp', 'jtucsella', 'lprzxBRMmL', false, '2021-11-23', 87, 74, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (12, 'Giustina', 'Raggatt', 'graggattb@goo.ne.jp', 'graggattb', 'HwUVW85f', false, '2021-12-22', 31, 16, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (19, 'Stinky', 'Thoms', 'sthomsi@hexun.com', 'sthomsi', 'mtRylJy', false, '2021-07-01', 40, 30, false);
INSERT INTO public.abonne (abn_id, abn_nom, abn_prenom, abn_email, abn_pseudo, abn_mdp, abn_admin, abn_date_inscrit, abn_nb_news, abn_nb_news_valid, abn_conf) VALUES (20, 'Carena', 'Beckmann', 'cbeckmannj@altervista.org', 'cbeckmannj', 'vmj2bRE', false, '2021-03-06', 51, 95, false);


INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (1, 'Industrial', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (2, 'Garden', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (3, 'Sports', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (4, 'Music', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (5, 'Festival', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (6, 'Clothing', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (7, 'IT', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (8, 'Outdoors', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (9, 'Shoes', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (10, 'Health', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (11, 'Movies', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (12, 'Books', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (13, 'Automotive', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (14, 'Baby', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (15, 'IA', 'en attente');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (16, 'Jewelry', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (17, 'Algorithm', 'non valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (18, 'Cat', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (19, 'Mazette', 'valide');
INSERT INTO public.domaine (dom_id, dom_nom, dom_etat) VALUES (20, 'Gandalf', 'valide');



INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (10, 6);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 16);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (3, 16);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (14, 17);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (1, 5);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (6, 19);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 6);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (16, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (19, 13);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (16, 14);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (1, 19);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 7);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (17, 15);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (17, 3);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (20, 18);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (7, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (12, 9);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (20, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (17, 15);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (20, 17);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (3, 4);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (13, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (3, 3);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (12, 15);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (8, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (16, 5);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 4);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (18, 6);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (13, 17);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (12, 13);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (6, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (2, 19);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (19, 15);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (1, 6);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (14, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (2, 1);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (5, 2);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (4, 14);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 10);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (16, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (20, 8);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (17, 1);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (13, 2);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (7, 9);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (6, 3);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (17, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (13, 17);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (4, 2);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (15, 7);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (9, 13);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (10, 13);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (20, 20);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (9, 5);
INSERT INTO public.domaineprivilegie (dpl_abn_id, dpl_dom_id) VALUES (2, 11);


INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (1, 'Triple-buffered');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (2, 'needs-based');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (3, 'superstructure');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (4, 'pricing structure');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (5, 'human-resource');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (6, 'Sharable');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (7, 'Face to face');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (8, 'intangible');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (9, 'neutral');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (10, 'knowledge user');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (11, 'Inverse');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (12, 'policy');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (13, 'Profit-focused');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (14, 'content-based');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (15, 'synergy');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (16, 'Intuitive');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (17, 'capability');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (18, 'bi-directional');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (19, 'Down-sized');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (20, 'Phased');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (21, 'mobile');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (22, 'De-engineered');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (23, 'disintermediate');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (24, 'Grass-roots');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (25, 'homogeneous');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (26, 'Cross-platform');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (27, 'core');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (28, 'tertiary');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (29, 'groupware');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (30, 'attitude');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (31, 'ability');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (32, 'intermediate');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (33, 'Upgradable');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (34, 'emulation');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (35, 'secondary');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (36, 'artificial intelligence');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (37, 'approach');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (38, 'Networked');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (39, 'reciprocal');
INSERT INTO public.motsclefs (mtc_id, mtc_nom) VALUES (40, 'knowledge base');


INSERT INTO public.objetevalue (obe_id) VALUES (1);
INSERT INTO public.objetevalue (obe_id) VALUES (2);
INSERT INTO public.objetevalue (obe_id) VALUES (3);
INSERT INTO public.objetevalue (obe_id) VALUES (4);
INSERT INTO public.objetevalue (obe_id) VALUES (5);
INSERT INTO public.objetevalue (obe_id) VALUES (6);
INSERT INTO public.objetevalue (obe_id) VALUES (7);
INSERT INTO public.objetevalue (obe_id) VALUES (8);
INSERT INTO public.objetevalue (obe_id) VALUES (9);
INSERT INTO public.objetevalue (obe_id) VALUES (10);
INSERT INTO public.objetevalue (obe_id) VALUES (11);
INSERT INTO public.objetevalue (obe_id) VALUES (12);
INSERT INTO public.objetevalue (obe_id) VALUES (13);
INSERT INTO public.objetevalue (obe_id) VALUES (14);
INSERT INTO public.objetevalue (obe_id) VALUES (15);
INSERT INTO public.objetevalue (obe_id) VALUES (16);
INSERT INTO public.objetevalue (obe_id) VALUES (17);
INSERT INTO public.objetevalue (obe_id) VALUES (18);
INSERT INTO public.objetevalue (obe_id) VALUES (19);
INSERT INTO public.objetevalue (obe_id) VALUES (20);

INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (17, 'Front-line coherent productivity', '2021-05-30', 'validee', 6, 10, 2, 5, 10);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (6, 'Exclusive content-based data-warehouse', '2021-11-06', 'non validee', 11, 15, 16, 9, 9);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (4, 'Vision-oriented fault-tolerant project', '2021-03-23', 'non validee', 1, 11, 12, 18, 19);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (9, 'Reduced human-resource forecast', '2021-02-27', 'non validee', 10, 18, 17, 13, 17);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (20, 'Cloned web-enabled instruction set', '2021-12-29', 'validee', 6, 10, 6, 12, 13);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (2, 'Up-sized solution-oriented model', '2021-08-08', 'non validee', 18, 5, 13, 9, 19);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (14, 'Focused 3rd generation software', '2021-05-14', 'validee', 8, 10, 17, 10, 4);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (8, 'Ameliorated discrete matrix', '2021-02-09', 'fausse', 8, 4, 8, 20, 19);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (12, 'Enterprise-wide methodical approach', '2021-07-07', 'validee', 10, 18, 8, 7, 20);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (15, 'Integrated client-server task-force', '2021-09-04', 'validee', 11, 19, 4, 17, 19);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (16, 'Optimized systematic application', '2021-02-08', 'fausse', 3, 20, 15, 16, 3);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (3, 'Intuitive fresh-thinking alliance', '2021-09-28', 'validee', 10, 15, 20, 20, 16);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (7, 'Customer-focused exuding framework', '2021-05-08', 'non validee', 6, 2, 5, 9, 11);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (1, 'Fundamental global policy', '2021-05-04', 'validee', 6, 6, 11, 6, 5);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (5, 'Stand-alone optimizing paradigm', '2021-06-14', 'validee', 8, 9, 2, 13, 9);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (19, 'Seamless 5th generation projection', '2021-06-11', 'non validee', 18, 18, 20, 2, 4);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (18, 'Focused content-based model', '2021-03-24', 'non validee', 18, 2, 18, 19, 11);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (13, 'Organic empowering Graphical User Interface', '2021-02-19', 'fausse', 10, 12, 19, 6, 11);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (11, 'Devolved background firmware', '2021-11-20', 'fausse', 5, 6, 20, 12, 13);
INSERT INTO public.archivagenews (arc_id, arc_texte, arc_date_archivage, arc_etat, arc_abn_id, arc_mtc_1, arc_mtc_2, arc_mtc_3, arc_dom_id) VALUES (10, 'Object-based web-enabled leverage', '2021-08-06', 'fausse', 4, 6, 13, 20, 4);



INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (1, 4, 12, 'Balanced impactful initiative', '2021-02-01');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (2, 10, 11, 'Realigned national knowledge base', '2021-09-19');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (3, 1, 12, 'Grass-roots zero tolerance superstructure', '2021-10-17');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (4, 11, 4, 'Networked cohesive website', '2021-05-13');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (5, 19, 10, 'Vision-oriented systematic budgetary management', '2021-11-07');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (6, 16, 12, 'Cloned demand-driven Graphical User Interface', '2021-06-16');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (7, 5, 1, 'Vision-oriented well-modulated hierarchy', '2021-09-21');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (8, 2, 12, 'Streamlined didactic ability', '2021-07-03');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (9, 19, 13, 'Assimilated holistic Graphic Interface', '2021-05-19');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (10, 11, 10, 'Compatible coherent process improvement', '2021-03-12');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (11, 4, 15, 'Automated full-range algorithm', '2021-06-07');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (12, 9, 15, 'Front-line dynamic parallelism', '2021-02-24');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (13, 14, 11, 'Distributed regional functionalities', '2021-08-11');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (14, 13, 8, 'Team-oriented zero defect focus group', '2021-03-16');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (15, 12, 11, 'Configurable mission-critical info-mediaries', '2021-11-27');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (16, 1, 7, 'Pre-emptive zero defect Graphical User Interface', '2021-02-09');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (17, 5, 13, 'Progressive exuding forecast', '2021-03-12');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (18, 1, 6, 'Reactive intermediate methodology', '2021-05-10');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (19, 11, 6, 'Enterprise-wide contextually-based parallelism', '2021-09-08');
INSERT INTO public.aevalue (eval_id, eval_abn_id, eval_objet, eval_justification, eval_date_justifcation) VALUES (20, 3, 2, 'Fully-configurable 3rd generation secured line', '2021-05-09');



INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (12, 'Quality-focused mobile model', '2021-03-12', 'non valide', 10, 15, 14, 3, 7);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (11, 'Re-contextualized non-volatile hub', '2021-09-29', 'valide', 17, 8, 20, 10, 2);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (10, 'Total reciprocal budgetary management', '2021-10-24', 'valide', 1, 2, 9, 6, 13);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (18, 'Visionary next generation core', '2021-02-28', 'en attente', 18, 7, 10, 14, 3);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (7, 'Cross-platform well-modulated archive', '2021-05-09', 'valide', 15, 4, 3, 9, 13);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (6, 'Synchronised impactful process improvement', '2021-07-26', 'valide', 16, 14, 11, 10, 13);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (9, 'Synchronised secondary hardware', '2021-04-23', 'valide', 5, 1, 7, 19, 16);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (8, 'Switchable value-added pricing structure', '2021-03-28', 'valide', 5, 11, 12, 1, 13);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (20, 'Distributed national circuit', '2022-01-20', 'en attente', 2, 13, 10, 4, 10);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (19, 'Up-sized cohesive synergy', '2021-12-27', 'en attente', 10, 17, 12, 19, 2);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (3, 'Monitored logistical success', '2022-01-15', 'en attente', 11, 9, 5, 10, 10);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (17, 'Intuitive asymmetric adapter', '2021-12-28', 'non valide', 11, 18, 15, 17, 1);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (13, 'Robust web-enabled info-mediaries', '2021-08-21', 'non valide', 10, 2, 16, 1, 20);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (16, 'User-friendly 6th generation open system', '2021-11-18', 'non valide', 16, 1, 20, 16, 4);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (2, 'Ameliorated bi-directional attitude', '2021-03-24', 'en attente', 12, 16, 7, 4, 13);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (1, 'Team-oriented local hierarchy', '2021-01-24', 'en attente', 15, 19, 3, 1, 12);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (14, 'Programmable non-volatile conglomeration', '2021-05-18', 'non valide', 20, 17, 13, 16, 4);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (5, 'Vision-oriented systematic task-force', '2021-10-24', 'valide', 3, 10, 4, 10, 19);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (15, 'Fundamental hybrid product', '2021-03-04', 'non valide', 1, 12, 4, 20, 9);
INSERT INTO public.news (new_id, new_texte, new_date_creation, new_etat, new_abn_id, new_mtc_1, new_mtc_2, new_mtc_3, new_dom_id) VALUES (4, 'Fundamental zero administration pricing structure', '2021-12-19', 'en attente', 3, 2, 12, 10, 3);

INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (1, 16, 'Future-proofed bandwidth-monitored capability');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (2, 4, 'Distributed radical capacity');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (3, 2, 'Diverse client-driven secured line');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (4, 13, 'Optional discrete challenge');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (5, 3, 'Synergized disintermediate secured line');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (6, 4, 'Front-line human-resource focus group');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (7, 14, 'Re-contextualized 6th generation protocol');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (8, 17, 'Assimilated logistical protocol');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (9, 17, 'Customer-focused zero defect customer loyalty');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (10, 11, 'Grass-roots clear-thinking middleware');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (11, 20, 'Pre-emptive value-added adapter');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (12, 17, 'Up-sized stable attitude');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (13, 20, 'Programmable 4th generation emulation');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (14, 6, 'Enterprise-wide intermediate methodology');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (15, 8, 'Down-sized next generation framework');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (16, 20, 'Front-line optimizing data-warehouse');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (17, 2, 'Reduced dedicated methodology');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (18, 4, 'Multi-tiered heuristic instruction set');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (19, 17, 'Multi-tiered even-keeled installation');
INSERT INTO public.notification (ntf_id, ntf_abn_id, ntf_contenu) VALUES (20, 15, 'Diverse fresh-thinking adapter');


