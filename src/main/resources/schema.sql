-- Lager tables når serveren kjøres

create table Motorvogner
(
    personNr varchar(11) not null,
    navn     varchar(40) not null,
    adresse  varchar(40) not null,
    regNr    varchar(40) not null,
    merke    varchar(40) not null,
    biltype  varchar(40) not null,
--     Perhaps not have primary key to allow for double inputs
--     Rather, perhaps have the regNr as primary key, since that is a natural key
    primary key (regNr)
);

create table Biler
(
    merke varchar(10) not null,
    modell varchar(20) not null
);

create table Bruker
(
    navn varchar(15),
    passord varchar(255),
    primary key (navn)
)