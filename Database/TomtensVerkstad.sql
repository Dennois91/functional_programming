drop database if exists tomtedatabase;
create database tomtedatabase;
use tomtedatabase;


create table elf
(
    id   int         not null auto_increment primary key,
    name varchar(30) not null
);

create table blacklist
(
    id                 int         not null auto_increment primary key,
    oldId              int         not null,
    formerEmployeeName varchar(30) not null
);

create table intelligenceelf
(
    id            int not null auto_increment primary key,
    elfId         int not null,
    securityLevel int not null,
    foreign key (elfId) references elf (id) on delete cascade
);

create table makerelf
(
    id    int not null auto_increment primary key,
    elfId int not null,
    foreign key (elfId) references elf (id) on delete cascade
);

create table country
(
    id                           int         not null auto_increment primary key,
    name                         varchar(30) not null,
    accountableIntelligenceElfId int,
    unique (name),
    foreign key (accountableIntelligenceElfId) references intelligenceelf (id) on delete set null
);

create table child
(
    id                           int         not null auto_increment primary key,
    name                         varchar(30) not null,
    address                      varchar(30) not null,
    countryId                    int         not null,
    accountableIntelligenceElfId int,
    foreign key (countryId) references country (id),
    foreign key (accountableIntelligenceElfId) references intelligenceelf (id) on delete set null
);

create table present
(
    id                    int         not null auto_increment primary key,
    accountableMakerElfId int,
    name                  varchar(50) not null,
    foreign key (accountableMakerElfId) references makerelf (id) on delete set null
);

create table wishes
(
    id        int not null auto_increment primary key,
    priority  int not null,
    childId   int not null,
    presentId int not null,
    foreign key (childId) references child (id) on delete cascade,
    foreign key (presentId) references present (id)
);

create table gets
(
    id        int not null auto_increment primary key,
    childId   int not null,
    presentId int,
    foreign key (childId) references child (id) on delete cascade,
    foreign key (presentId) references present (id) on delete set null
);

create table report
(
    id         int  not null auto_increment primary key,
    niceOrNot  bool not null,
    childId    int  not null,
    reporterId int,
    reviewerId int,
    reportDate date not null,
    foreign key (childId) references child (id) on delete cascade,
    foreign key (reporterId) references intelligenceelf (id) on delete set null,
    foreign key (reviewerId) references intelligenceelf (id) on delete set null
);

insert into elf (name)
values ('Santa'),
       ('Glader'),
       ('Toker'),
       ('Trotter'),
       ('Prosit'),
       ('Butter'),
       ('Glader'),
       ('Blyger'),
       ('Blyger'),
       ('Skumtomten');

insert into intelligenceelf (elfId, securityLevel)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 3);

insert into makerelf (elfId)
values (5),
       (6),
       (7),
       (8),
       (9);

insert into country (name, accountableIntelligenceElfId)
values ('Sweden', 1),
       ('Iceland', 1),
       ('England', 1),
       ('France', 1),
       ('USA', 1);

insert into present (name, accountableMakerElfId)
values ('phone', 1),
       ('laptop', 1),
       ('doll', 2),
       ('car', 3),
       ('book', 4),
       ('pencils', 4),
       ('skateboard', 3),
       ('ball', 2),
       ('cat', 5),
       ('socks', 4);

insert into child (name, address, countryId, accountableIntelligenceElfId)
values ('Ambrosia', 'Globen', 1, 4),
       ('Bertil', 'Stadshuset', 1, 4),
       ('Camelia', 'Perlan', 2, 4),
       ('Doris', 'Big Ben', 3, 4),
       ('Efraim', 'Madame Tusseaus', 3, 4),
       ('Finnegan', 'Eiffel Tower', 4, 4),
       ('Greger', 'White House', 5, 4),
       ('Hulken', 'Empire State Building', 5, 4);


insert into wishes (priority, childId, presentId)
values (1, 1, 4),
       (2, 1, 9),
       (1, 2, 9),
       (1, 3, 6),
       (2, 3, 7),
       (3, 3, 8),
       (1, 4, 9),
       (1, 5, 4),
       (1, 6, 2),
       (1, 7, 2),
       (2, 7, 1),
       (3, 7, 5);

insert into gets (childId, presentId)
values (1, 4),
       (2, 9),
       (3, 6),
       (4, 9),
       (5, 10),
       (6, 2),
       (7, 5);

insert into report (niceOrNot, childId, reporterId, reviewerId, reportDate)
values (1, 1, 3, 2, '2017-12-14'),
       (1, 2, 3, 2, '2017-12-18'),
       (1, 3, 3, 2, '2017-12-16'),
       (1, 4, 3, 2, '2017-12-21'),
       (1, 5, 3, 2, '2017-12-13'),
       (1, 6, 3, 2, '2017-12-19'),
       (0, 7, 3, 2, '2017-12-14'),
       (0, 8, 3, 2, '2017-12-13');

-- uppgift 11;

select child.name
from child
where id = 3;

select child.address
from child
where id = 3;

select present.name, present.accountableMakerElfId
from present;

select child.name
from child
         join report
where childId = child.id
  and report.niceOrNot = 1;

select child.name
from child
         inner join report on child.id = report.childId
         inner join country on child.countryId = country.id
where report.niceOrNot = 1
  and country.name = 'Sweden';

select child.name
from child
where child.address = 'Eiffel Tower';

select child.name
from child
where address like 'e%';

select child.address, child.name
from child
where child.name like '%a%';

select child.id, child.name, child.address
from child
where child.address = 'Eiffel Tower'
   or child.address = 'Globen';

-- Uppgift 12

select child.name, country.name
from child,
     country
where child.countryId = country.id;

select child.name, present.name
from child,
     gets,
     present
where gets.childId = child.id
  and gets.id = present.id;

select distinct present.name
from present
         inner join gets on present.id = presentId;

select child.name, present.name, wishes.priority
from child
         inner join wishes on child.id = wishes.childId
         inner join present on wishes.presentId = present.id;

select child.name
from child
         inner join gets on child.id = gets.childId
         inner join present on gets.presentId = present.id
where present.name = 'car';

select distinct country.name
from child
         INNER JOIN country on child.countryId = country.id
         inner join gets on child.id = gets.childId
         inner join present on gets.presentId = present.id
where present.name = 'car';

select child.name
from child
         inner join wishes on child.id = wishes.childId
         inner join gets on child.id = gets.childId
where wishes.presentId = gets.presentId;

-- Uppgift 13

select child.name, child.address
from child
         inner join country on child.countryId = country.id
where country.name = 'Sweden';

select child.name, present.name
from child
         INNER JOIN gets on child.id = gets.childId
         INNER JOIN present on gets.presentId = present.id
order by present.name;

select child.name, present.name, country.name
from child
         INNER JOIN gets on child.id = gets.childId
         INNER JOIN present on gets.presentId = present.id
         INNER JOIN country on child.countryId = country.id
ORDER BY country.name DESC;

select child.name, wishes.presentId
from child
         INNER JOIN wishes on child.id = wishes.childId
where wishes.presentId >= 2
  and wishes.presentId <= 5;

select present.name, country.name
from child
         INNER JOIN gets on child.id = gets.childId
         INNER JOIN country on child.countryId = country.id
         INNER JOIN present on gets.presentId = present.id
order by country.name;

select child.name, present.name
from child
         inner join wishes on wishes.childId = child.id
         inner join present on present.id = wishes.presentId
limit 3;


select COUNT(child.name) as AntalBarn, country.name
from child
         INNER JOIN country
                    on child.countryId = country.id
group by country.name;

-- Uppgift 17 Vyer

drop view if exists nicekids;
CREATE view NiceKids as
select child.name, child.address
from child
         inner join report on child.id = report.childId
where niceOrNot = true;

CREATE view Present_Top5 as
select present.name, COUNT(wishes.presentId)
from present
         inner join wishes on present.id = wishes.presentId
group by wishes.presentId
ORDER BY COUNT(wishes.presentId) DESC
limit 5;

CREATE VIEW All_Gifts_Blueprints as
select present.name, present.accountableMakerElfId
from present;

INSERT INTO all_gifts_blueprints
    (name)
values ('Dildo');

CREATE VIEW AllKidsScore as
select child.name,
       CASE report.niceOrNot
           WHEN 1 THEN 'Nice'
           WHEN 0 THEN 'Evil'
           END AS niceOrNot
from child
         inner join report on child.id = report.childId;

CREATE VIEW AllKidsScorePlus as
select child.name as ChildName,
       child.address,
       country.name as CountryName,
       CASE report.niceOrNot
           WHEN 1 THEN 'Nice'
           WHEN 0 THEN 'Evil'
           END      AS niceOrNot
From child
         INNER JOIN report on child.id = report.childId
         INNER JOIN country ON child.countryId = country.id;
