package rs.ac.uns.ftn.sbnz.rentcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Osoba;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Korisnik findOneByEmail(String email);
}
