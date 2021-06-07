package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.AutoService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.KorisnikService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.RezervacijaMapper;

import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/korisnik", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AutoService autoService;

    private RezervacijaMapper rezervacijaMapper;

    public KorisnikController() {
        this.rezervacijaMapper = new RezervacijaMapper();
    }

    @GetMapping("/sva-iznajmljivanja")
    public ResponseEntity<List<RezervacijaDto>> svaIznajmljivanja(){
        Korisnik korisnik = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Rezervacija> iznajmljivanjaList = korisnikService.findAllIznajmljivanja(korisnik);

        return new ResponseEntity<>(rezervacijaMapper.toDtoList(iznajmljivanjaList), HttpStatus.OK);
    }
}
