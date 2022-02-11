-- setter data inn i biler tabelen
insert into Biler(merke, modell)
values ('Volvo', 'V40'),
       ('Volvo', 'V60'),
       ('Volvo', 'XC90'),
       ('Toyota', 'Corolla'),
       ('Toyota', 'Levin'),
       ('Toyota', 'Trueno'),
       ('Volkswagen', 'Golf'),
       ('Volkswagen', 'Polo'),
       ('Volkswagen', 'Touran'),
       ('Subaru', 'Impreza WRX'),
       ('Honda', 'Civic'),
       ('Mazda', 'RX7');

insert into Motorvogner(personNr, navn, adresse, regNr, merke, biltype)
values ('18084781897', 'Takumi', 'Mt. Akina', '13-954', 'Toyota', 'Trueno'),
       ('22019391322', 'Shingo', 'Kyoto', '46-037', 'Honda', 'Civic'),
       ('23119993939', 'Keisuke', 'Saitama', '63-887', 'Mazda', 'RX7');

insert into Bruker(navn, passord)
values ('admin', 'password123');
