insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');

INSERT INTO osobe (tip, email, ime, prezime, lozinka, status) VALUES
('Korisnik', 'email1@email.com', 'Ime1', 'Prezime1', '$2a$10$A2Scp4L/2Zj9bRvs/O9Li.dODxCDqno4VXT5XWNRS5amuBK5/KIYu', 'OBICNI');

INSERT INTO osobe (tip, email, ime, prezime, lozinka) VALUES
('Administrator', 'email2@email.com', 'Ime1', 'Prezime1', '$2a$10$A2Scp4L/2Zj9bRvs/O9Li.dODxCDqno4VXT5XWNRS5amuBK5/KIYu');

INSERT INTO osobe (tip, email, ime, prezime, lozinka, status) VALUES
('Korisnik', 'user@gmail.com', 'Ime1', 'Prezime1', '$2a$10$A2Scp4L/2Zj9bRvs/O9Li.dODxCDqno4VXT5XWNRS5amuBK5/KIYu', 'OBICNI');


INSERT INTO osoba_authority(osoba_id, authority_id) values (1,2);
INSERT INTO osoba_authority(osoba_id, authority_id) values (2,1);
INSERT INTO osoba_authority(osoba_id, authority_id) values (3,2);

INSERT INTO marka (naziv) values ('Smart');
INSERT INTO marka (naziv) values ('Tesla');
INSERT INTO marka (naziv) values ('Ferarri');
INSERT INTO marka (naziv) values ('BMW');
INSERT INTO marka (naziv) values ('Mercedes');
INSERT INTO marka (naziv) values ('Skoda');
INSERT INTO marka (naziv) values ('Fiat');
INSERT INTO marka (naziv) values ('Toyota');

INSERT INTO auto (
broj_sedista,
cena,
distanca,
duzina,
godiste,
karoserija,
maksimalna_brzina,
model,
sirina,
tip_goriva,
ubrzanje,
visina,
zapremina_gepeka,
zapremina_rezervoara,
marka_id,
bodovi,
prosecna_ocena
) VALUES(
2,
15,
0,
2.5,
2015,
'KUPE',
180,
'Fortwo',
3,
'DIZEL',
5,
1.5,
100,
300,
1,
0,
 0
);

INSERT INTO auto (
broj_sedista,
cena,
distanca,
duzina,
godiste,
karoserija,
maksimalna_brzina,
model,
sirina,
tip_goriva,
ubrzanje,
visina,
zapremina_gepeka,
zapremina_rezervoara,
marka_id,
bodovi,
prosecna_ocena
) VALUES(
2, --broj_sedista
17, --cena
200, --distanca
2.5, --duzina
2015, --godiste
'KUPE', --karoserija
180, --max_brzina
'Fortwo', --model
3, --sirina
'ELEKTRICNI', --tipGoriva
5, --ubrzanje
1.5, --visina
100, --zapGepeka
0, --zapRezervoara
1, --marka
0,
 0
);

INSERT INTO auto (
broj_sedista,
cena,
distanca,
duzina,
godiste,
karoserija,
maksimalna_brzina,
model,
sirina,
tip_goriva,
ubrzanje,
visina,
zapremina_gepeka,
zapremina_rezervoara,
marka_id,
bodovi,
prosecna_ocena
) VALUES(
5, --broj_sedista
25, --cena
0, --distanca
4.5, --duzina
2015, --godiste
'KARAVAN', --karoserija
220, --max_brzina
'OKTAVIJA', --model
3.2, --sirina
'BENZIN', --tipGoriva
4, --ubrzanje
2, --visina
400, --zapGepeka
600, --zapRezervoara
6, --marka
0,
 0
);

INSERT INTO auto (
broj_sedista,
cena,
distanca,
duzina,
godiste,
karoserija,
maksimalna_brzina,
model,
sirina,
tip_goriva,
ubrzanje,
visina,
zapremina_gepeka,
zapremina_rezervoara,
marka_id,
bodovi,
prosecna_ocena
) VALUES(
2, --broj_sedista
70, --cena
0, --distanca
3.8, --duzina
2017, --godiste
'KUPE', --karoserija
350, --max_brzina
'458', --model
3.1, --sirina
'BENZIN', --tipGoriva
2.2, --ubrzanje
2, --visina
100, --zapGepeka
450, --zapRezervoara
3, --marka
0,
 0
);


INSERT INTO auto (
broj_sedista,
cena,
distanca,
duzina,
godiste,
karoserija,
maksimalna_brzina,
model,
sirina,
tip_goriva,
ubrzanje,
visina,
zapremina_gepeka,
zapremina_rezervoara,
marka_id,
bodovi,
prosecna_ocena
) VALUES(
5, --broj_sedista
40, --cena
500, --distanca
3.8, --duzina
2017, --godiste
'KARAVAN', --karoserija
250, --max_brzina
'X', --model
3.1, --sirina
'ELEKTRICNI', --tipGoriva
3.2, --ubrzanje
2, --visina
600, --zapGepeka
0, --zapRezervoara
2, --marka
0,
0
);


INSERT INTO rezervacija(
broj_dana,
ocena,
iznos,
kraj_rezervacije,
pocetak_rezervacije,
status,
auto_id,
korisnik_id)
VALUES (10, 0,1000, '2020-1-10', '2020-1-1', 'KREIRANA', 1, 1);


INSERT INTO rezervacija(
broj_dana,
ocena,
iznos,
kraj_rezervacije,
pocetak_rezervacije,
status,
auto_id,
korisnik_id
)
VALUES (15,0, 1000, '2020-2-15', '2020-2-1', 'PRIHVACENA', 2, 3);


INSERT INTO rezervacija(
broj_dana,
ocena,
iznos,
kraj_rezervacije,
pocetak_rezervacije,
status,
auto_id,
korisnik_id)
VALUES (10, 0,1000, '2020-1-10', '2020-1-1', 'ODBIJENA', 1, 1);


INSERT INTO rezervacija(
broj_dana,
ocena,
iznos,
kraj_rezervacije,
pocetak_rezervacije,
status,
auto_id,
korisnik_id)
VALUES (10, 0, 1000, '2020-1-10', '2020-1-1', 'PRIHVACENA', 1, 3);


INSERT INTO rezervacija(
broj_dana,
ocena,
iznos,
kraj_rezervacije,
pocetak_rezervacije,
status,
auto_id,
korisnik_id
)
VALUES (13,0, 5100, '2020-3-13', '2020-1-1', 'KREIRANA', 3, 1);

INSERT INTO ocena (datum, vrednost, auto_id, korisnik_id) VALUES
('2020-3-13', 5, 1, 1),
('2019-3-17', 4, 1, 3),
('2020-2-14', 4, 1, 3),
('2020-3-21', 4, 1, 1),
('2020-1-30', 5, 1, 1),
('2020-4-26', 5, 1, 1),
('2020-5-18', 5, 1, 1),
('2020-3-19', 5, 1, 3),
('2020-1-12', 5, 1, 3),
('2020-5-17', 5, 1, 1),
('2020-6-13', 5, 1, 1),
('2020-7-21', 5, 1, 3),
('2020-1-30', 5, 2, 3),
('2020-4-26', 5, 2, 3),
('2020-5-18', 5, 2, 1),
('2020-3-19', 5, 2, 1),
('2020-1-12', 3, 2, 3),
('2020-5-17', 5, 2, 3),
('2020-6-13', 3, 2, 3),
('2020-1-30', 5, 3, 3),
('2020-4-26', 4, 3, 3),
('2020-5-18', 5, 3, 3),
('2020-3-19', 5, 3, 3),
('2020-1-12', 5, 3, 1),
('2020-5-17', 5, 4, 1),
('2020-6-13', 5, 4, 1),
('2020-1-30', 5, 4, 1),
('2020-4-26', 4, 4, 1),
('2020-5-18', 5, 4, 1),
('2020-3-19', 5, 4, 1),
('2020-1-12', 3, 4, 3),
('2020-5-17', 3, 4, 3),
('2020-6-13', 3, 5, 1),
('2020-1-30', 5, 5, 1),
('2020-4-26', 5, 5, 1),
('2020-5-18', 5, 5, 3),
('2020-3-19', 5, 5, 3),
('2020-1-12', 3, 5, 3),
('2020-5-17', 3, 3, 1),
('2020-6-13', 5, 3, 1);


