package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.PostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Administrator;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Osoba;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusKorisnika;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.KorisnikRepository;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.OsobaRepository;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    OsobaRepository osobaRepository;

    public Korisnik dodajNovogKorisnika(Korisnik korisnik) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        korisnik.setLozinka(encoder.encode(korisnik.getLozinka()));
        korisnik.setStatus(StatusKorisnika.OBICNI);
        Osoba osoba = (Osoba) osobaRepository.findOneByEmail(korisnik.getEmail());
        if(osoba == null){
            Korisnik sacuvani = korisnikRepository.save(korisnik);
            return sacuvani;
        }
        else {
            if (osoba instanceof Administrator)
                throw new PostojeciObjekatException("Korisnik", "email");
            throw new Exception("Doslo je do greske");
        }

    }

    public Korisnik findOneByEmail(String email){
        Korisnik korisnik = korisnikRepository.findOneByEmail(email);
        if(korisnik == null){
            throw new UsernameNotFoundException(String.format("Nije pronadjen korisnik sa emailom: %s.", email));
        }
        return korisnik;
    }
}
