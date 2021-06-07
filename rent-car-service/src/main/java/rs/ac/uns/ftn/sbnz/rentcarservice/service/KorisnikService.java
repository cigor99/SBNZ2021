package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.PostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.KorisnikRepository;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.OsobaRepository;

import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private OsobaRepository osobaRepository;

    @Autowired
    private RezervacijaService rezervacijaService;

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

    public List<Rezervacija> findAllIznajmljivanja(Korisnik ulogovani) {

        Korisnik korisnik = korisnikRepository.findOneByEmail(ulogovani.getEmail());

        List<Rezervacija> korisnikoveRezervacije = rezervacijaService.findAllByKorisnikId(korisnik.getId());

        return korisnikoveRezervacije;

    }
}
