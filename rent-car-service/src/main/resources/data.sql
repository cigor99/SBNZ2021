insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');

INSERT INTO osobe (tip, email, ime, prezime, lozinka, status) VALUES
('Korisnik', 'email1@email.com', 'Ime1', 'Prezime1', '$2a$10$A2Scp4L/2Zj9bRvs/O9Li.dODxCDqno4VXT5XWNRS5amuBK5/KIYu', 'OBICNI');

INSERT INTO osobe (tip, email, ime, prezime, lozinka) VALUES
('Administrator', 'email2@email.com', 'Ime1', 'Prezime1', '$2y$12$cK0RcWsU1ZLamAx.gaHPYeYGaXsvCfhPl2n.lkCyAQ6pdEUZ2ZHoe');

INSERT INTO osoba_authority(osoba_id, authority_id) values (1,2);
INSERT INTO osoba_authority(osoba_id, authority_id) values (2,1);