DROP DATABASE IF EXISTS skoButik;
CREATE DATABASE skoButik;
USE skoButik;

CREATE TABLE färg
(
    id   int         not null auto_increment primary key,
    färg varchar(10) not null
);

CREATE TABLE märke
(
    id    int         not null auto_increment primary key,
    märke varchar(20) not null
);

CREATE TABLE model
(
    id    int         not null auto_increment primary key,
    model varchar(20) not null
);

CREATE TABLE storlek
(
    id      int not null auto_increment primary key,
    storlek int not null
);

CREATE TABLE pris
(
    id      int not null auto_increment primary key,
    pris    int not null,
    modelId int,
    märkeId int,
    skapad  timestamp default current_timestamp,
    ändrad  timestamp default current_timestamp on update current_timestamp,
    foreign key (modelId) references model (id) on delete set null,
    foreign key (märkeId) references märke (id) on delete set null
);

CREATE TABLE Kategori
(
    id       int         not null auto_increment primary key,
    kategori varchar(10) not null
);

CREATE TABLE sko
(
    id         int not null auto_increment primary key,
    märkeId    int not null,
    modelId    int not null,
    storlekId  int not null,
    färgId     int not null,
    prisId     int not null,
    lagerSaldo int not null,
    skapad     timestamp default current_timestamp,
    ändrad     timestamp default current_timestamp on update current_timestamp,
    foreign key (märkeId) references märke (id),
    foreign key (modelId) references model (id),
    foreign key (storlekId) references storlek (id),
    foreign key (färgId) references färg (id),
    foreign key (prisId) references pris (id)

);

CREATE TABLE skoKatMapp
(
    id       int not null auto_increment primary key,
    skoId    int not null,
    kategori int not null
);

CREATE TABLE kund
(
    id     int          not null auto_increment primary key,
    email  varchar(100) not null,
    namn   varchar(100),
    adress varchar(100),
    skapad timestamp default current_timestamp,
    ändrad timestamp default current_timestamp on update current_timestamp
);

CREATE TABLE beställning
(
    id        int not null auto_increment primary key,
    kundId    int,
    totalPris int not null,
    skapad    timestamp default current_timestamp,
    ändrad    timestamp default current_timestamp on update current_timestamp,
    foreign key (kundId) references kund (id) on delete set null
    -- För att spara informationen om köp utan att behålla informationen om kund gör vi on delete set null
);

CREATE TABLE kundvagn
(
    id            int not null auto_increment primary key,
    antal         int not null,
    skoId         int not null,
    beställningId int,
    foreign key (skoId) references sko (id),
    foreign key (beställningId) references beställning (id)

);

INSERT INTO kategori (Id, Kategori)
VALUES (1, 'Man'),
       (2, 'Kvinna'),
       (3, 'Barn'),
       (4, 'Vinter'),
       (5, 'Sommar');

INSERT INTO storlek(Id, storlek)
VALUES (1, 36),
       (2, 37),
       (3, 38);

INSERT INTO model(id, model)
VALUES (1, 'känga'),
       (2, 'sandal'),
       (3, 'sneaker');

INSERT INTO märke(id, märke)
VALUES (1, 'ecco'),
       (2, 'adidas'),
       (3, 'nike');

INSERT INTO färg(ID, FÄRG)
VALUES (1, 'röd'),
       (2, 'svart'),
       (3, 'vit');

INSERT INTO pris(Id, pris, modelId, märkeId)
VALUES (1, 600, 1, 1),
       (2, 500, 2, 1),
       (3, 800, 1, 2),
       (4, 1100, 3, 2),
       (5, 900, 2, 2),
       (6, 1600, 3, 3),
       (7, 900, 2, 3),
       (8, 1900, 1, 3);

INSERT INTO SKO (MärkeId, ModelId, StorlekId, FärgId, PrisId, LagerSaldo)
VALUES (1, 1, 1, 1, 1, 10),
       (1, 1, 2, 1, 1, 10),
       (1, 1, 3, 1, 1, 10),
       (1, 2, 1, 2, 2, 10),
       (1, 2, 2, 2, 2, 10),
       (1, 2, 3, 2, 2, 10),
       (2, 2, 1, 2, 3, 10),
       (2, 2, 2, 2, 3, 10),
       (2, 2, 3, 2, 3, 10),
       (2, 3, 1, 3, 4, 10),
       (2, 3, 2, 3, 4, 10),
       (2, 3, 3, 3, 4, 10),
       (2, 2, 1, 2, 5, 10),
       (2, 2, 2, 2, 5, 10),
       (2, 2, 3, 2, 5, 10),
       (3, 3, 1, 1, 6, 10),
       (3, 3, 2, 1, 6, 10),
       (3, 3, 3, 1, 6, 10),
       (3, 2, 1, 3, 7, 10),
       (3, 2, 2, 3, 7, 10),
       (3, 2, 3, 3, 7, 10),
       (3, 1, 1, 2, 8, 10),
       (3, 1, 2, 2, 8, 10),
       (3, 1, 3, 2, 8, 10);

INSERT INTO skoKatMapp(skoId, kategori)
VALUES (1, 1),
       (1, 4),
       (2, 1),
       (2, 4),
       (3, 1),
       (3, 4),
       (4, 2),
       (4, 5),
       (5, 2),
       (5, 5),
       (6, 2),
       (6, 5),
       (7, 1),
       (7, 4),
       (8, 1),
       (8, 4),
       (9, 1),
       (9, 4),
       (10, 3),
       (10, 5),
       (11, 3),
       (11, 5),
       (12, 3),
       (12, 5),
       (13, 2),
       (13, 5),
       (14, 2),
       (14, 5),
       (15, 2),
       (15, 5),
       (16, 3),
       (16, 5),
       (17, 3),
       (17, 5),
       (18, 3),
       (18, 5),
       (19, 2),
       (19, 5),
       (20, 2),
       (20, 5),
       (21, 2),
       (21, 5),
       (22, 1),
       (22, 5),
       (23, 1),
       (23, 5),
       (24, 1),
       (24, 5);

INSERT INTO kund(email, namn, adress)
VALUES ('pelle@gmail.se', 'Pelle', 'Stockholm'),
       ('kalle@gmail.se', 'Kalle', 'Norrland'),
       ('telle@gmail.se', 'Telle', 'Skåne'),
       ('putte@gmail.se', 'Putte', 'Stockholm'),
       ('nutte@gmail.se', 'Nutte', 'Stockholm');

INSERT INTO beställning(kundId, totalPris)
VALUES (1, 1700),
       (1, 1100),
       (2, 3200),
       (3, 900),
       (4, 1600),
       (5, 2400);

INSERT INTO kundvagn (ANTAL, SKOID, BESTÄLLNINGID)
VALUES (1, 3, 1),
       (1, 3, 1),
       (1, 6, 1),
       (1, 1, 2),
       (1, 4, 2),
       (2, 24, 3),
       (1, 21, 4),
       (1, 17, 5),
       (3, 9, 6);

CREATE INDEX IX_Kundvagn_beställID on kundvagn (beställningId);
CREATE INDEX IX_Kundvagn_skoId on kundvagn (skoId);
-- Mellan desa idn görs det flest sökningar där inte PK används

-- STORE PROCEDURE NEDAN


DELIMITER //
DROP PROCEDURE IF EXISTS AddToCart //
CREATE PROCEDURE AddToCart(IN SetKundId int, IN beställningsId int, IN produktId int)
BEGIN
    DECLARE productPris int default -1;
    DECLARE lastIndex int default -1;
    DECLARE lagerCheck int default -1;
    DECLARE maxKunder int default -1;
    DECLARE maxProdukter int DEFAULT -1;
    DECLARE orderOwner int DEFAULT -1;
    DECLARE temp int DEFAULT -1;
    START TRANSACTION;


    select sko.lagersaldo from sko where sko.id = produktId INTO lagerCheck;
    select kundId from beställning where beställningsId = beställning.id INTO orderOwner;
    select MAX(id) from kund INTO maxKunder;
    select MAX(Id) from sko INTO maxProdukter;

    IF SetKundId IS NULL OR SetKundId = 0 OR beställningsId IS NULL OR produktId IS NULL THEN
        SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Inget värde får vara null';
    END IF;

    IF NOT EXISTS(select id FROM beställning WHERE id = beställningsId) THEN
        SET @temp = 1; -- Enbart för att THEN inte får vara tom OM beställnings ID inte finns vill jag gå vidare
    -- För att kunna hindra så att ingen kund som inte äger sin order kan lägga varor i en order
    -- Men om ordern inte finns så kan ju ingen äga den så denna if bypassar det.
    ELSEIF orderOwner NOT LIKE SetKundId THEN
        SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = 'Denna kund äger inte denna order';
    END IF;

    IF SetKundId > maxKunder THEN
        SIGNAL SQLSTATE '45003' SET MESSAGE_TEXT = 'Kunden finns ej';
    END IF;

    IF produktId > maxProdukter THEN
        SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Produkten Finns ej. Skriv ett lägre ID';
    end if;

    IF lagerCheck = 0 THEN
        SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = 'Skon är slut i lager';
    END IF;

    BEGIN
        DECLARE EXIT HANDLER FOR 1452
            BEGIN
                ROLLBACK;
                SIGNAL SQLSTATE '45006' SET MESSAGE_TEXT = 'Produkten finns inte. Skriv ett annat ID';
            END;
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                SIGNAL SQLSTATE '45007' SET MESSAGE_TEXT = 'Ett okänt fel har inträffat';
            END;

        IF NOT EXISTS(select beställning.id
                      from beställning
                      where beställning.id = beställningsId) THEN
            SELECT pris.pris
            FROM pris
                     JOIN sko ON pris.id = sko.prisId
            WHERE sko.id = produktId
            INTO productPris;
            INSERT INTO beställning(kundId, totalPris) VALUES (SetKundId, productPris);
            SELECT LAST_INSERT_ID() INTO lastIndex;
            INSERT INTO kundvagn(antal, skoId, beställningId) VALUES (1, produktId, lastIndex);
            UPDATE sko SET lagerSaldo = lagerSaldo - 1 WHERE sko.id = produktId;

            -- Om BeställningsId finns i beställning OCH produktId finns i kundvagn på samma rad som beställningsID

        ELSEIF EXISTS
            (SELECT 1
             FROM beställning
                      JOIN kundvagn ON beställning.id = kundvagn.beställningId
             WHERE beställning.id = beställningsId
               AND kundvagn.skoId = produktId)
        THEN
            UPDATE kundvagn
            SET kundvagn.antal = kundvagn.antal + 1
            where kundvagn.skoId = produktId
              AND kundvagn.beställningId = beställningsId;
            UPDATE sko SET lagerSaldo = lagerSaldo - 1 WHERE sko.id = produktId;
            SELECT pris.pris
            FROM pris
                     JOIN sko ON pris.id = sko.prisId
            WHERE sko.id = produktId
            INTO productPris;
            UPDATE beställning SET totalPris = totalPris + productPris WHERE beställning.id = beställningsId;

        ELSEIF EXISTS
            (select beställning.id
             from beställning
             where beställning.id = beställningsId)
        THEN

            INSERT INTO kundvagn(antal, skoId, beställningId) VALUES (1, produktId, beställningsId);
            SELECT pris.pris
            FROM pris
                     JOIN sko ON pris.id = sko.prisId
            WHERE sko.id = produktId
            INTO productPris;

            UPDATE beställning SET totalPris = totalPris + productPris WHERE beställning.id = beställningsId;
            UPDATE sko SET lagerSaldo = lagerSaldo - 1 WHERE sko.id = produktId;
        END IF;
    END;
    COMMIT;
END
//

DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS deleteKund //
CREATE PROCEDURE deleteKund(IN kundId INT)
BEGIN
    DELETE FROM kund WHERE id = kundId;
END //
DELIMITER ;

CALL AddToCart(5, 0, 24);
-- CALL deleteKund(5);