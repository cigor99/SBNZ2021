package rs.ac.uns.ftn.sbnz.rentcarservice.util;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;

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
                rezervacija.getAuto().getMarka().getNaziv(),
                rezervacija.getAuto().getModel(),
                rezervacija.getKorisnik().getEmail());
    }

    public List<RezervacijaDto> toDtoList(List<Rezervacija> rezervacije){
        List<RezervacijaDto> dtoList = new ArrayList<>();
        for(Rezervacija r: rezervacije)
            dtoList.add(this.toDto(r));
        return dtoList;
    }

}
