package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusRezervacije;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.AutoService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.KorisnikService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.RezervacijaService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.RezervacijaMapper;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/korisnik", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private RezervacijaService rezervacijaService;

    private RezervacijaMapper rezervacijaMapper;

    public KorisnikController() {
        this.rezervacijaMapper = new RezervacijaMapper();
    }

    @GetMapping()
    public ResponseEntity<List<RezervacijaDto>> svaIznajmljivanja(){
        Korisnik korisnik = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Rezervacija> iznajmljivanjaList = korisnikService.findAllIznajmljivanja(korisnik);

        iznajmljivanjaList = iznajmljivanjaList.stream().filter(iznajmljivanje -> iznajmljivanje.getStatus() == StatusRezervacije.PRIHVACENA).collect(Collectors.toList());

        return new ResponseEntity<>(rezervacijaMapper.toDtoList(iznajmljivanjaList), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RezervacijaDto> rezervisiAuto(@RequestBody RezervacijaDto rezervacijaDto){
        Rezervacija kreirana;
        try {
            Korisnik ulogovani = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            kreirana = rezervacijaService.rezervisiAuto(rezervacijaDto, ulogovani);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(rezervacijaMapper.toDto(kreirana), HttpStatus.ACCEPTED);
    }
}
