package rs.ac.uns.ftn.sbnz.rentcarservice.util;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnikDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Ocena;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusKorisnika;

import java.util.HashSet;

public class KorisnikMapper {

    public Korisnik toEntity(KorisnikDto korinsikDto){
        return new Korisnik(
                0,
                korinsikDto.getIme(),
                korinsikDto.getPrezime(),
                korinsikDto.getEmail(),
                korinsikDto.getLozinka(),
                StatusKorisnika.OBICNI,
                new HashSet<Ocena>(),
                new HashSet<Rezervacija>());
    }

    public KorisnikDto toDto(Korisnik korisnik) {
        return new KorisnikDto(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getEmail(),
                korisnik.getLozinka(),
                korisnik.getStatus().name());
    }
}
