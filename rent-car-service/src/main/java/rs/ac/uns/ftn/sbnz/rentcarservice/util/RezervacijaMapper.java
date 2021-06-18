package rs.ac.uns.ftn.sbnz.rentcarservice.util;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusRezervacije;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaMapper {

    public RezervacijaDto toDto(Rezervacija rezervacija){
        return new RezervacijaDto(
                rezervacija.getId(),
                rezervacija.getPocetakRezervacije(),
                rezervacija.getKrajRezervacije(),
                rezervacija.getStatus().name(),
                rezervacija.getBrojDana(),
                rezervacija.getIznos(),
                rezervacija.getAuto().getId(),
                rezervacija.getAuto().getMarka().getNaziv(),
                rezervacija.getAuto().getModel(),
                rezervacija.getKorisnik().getEmail(),
                rezervacija.getOcena());
    }

    public List<RezervacijaDto> toDtoList(List<Rezervacija> rezervacije){
        List<RezervacijaDto> dtoList = new ArrayList<>();
        for(Rezervacija r: rezervacije)
            dtoList.add(this.toDto(r));
        return dtoList;
    }

//    public Rezervacija toEntity(RezervacijaDto rezervacijaDto, Auto auto, Korisnik korisnik) {
//        return new Rezervacija(
//                0,
//                rezervacijaDto.getPocetakRezervacije(),
//                rezervacijaDto.getKrajRezervacije(),
//                StatusRezervacije.KREIRANA,
//                rezervacijaDto.getBrojDana(),
//                0,
//                auto,
//                korisnik);
//    }
}
