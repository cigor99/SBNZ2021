package rs.ac.uns.ftn.sbnz.rentcarservice.util;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.OcenaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Ocena;

public class OcenaMapper {

    public OcenaDto toDto(Ocena ocena){
        return new OcenaDto(
                ocena.getId(),
                ocena.getVrednost(),
                ocena.getDatum(),
                ocena.getAuto().getId(),
                ocena.getKorisnik().getEmail());
    }
}
