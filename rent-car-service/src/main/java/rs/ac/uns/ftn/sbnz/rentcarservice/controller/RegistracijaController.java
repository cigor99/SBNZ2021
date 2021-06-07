package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnikDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.KorisnikService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.KorisnikMapper;

@RestController
@RequestMapping(value = "api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistracijaController {

    @Autowired
    KorisnikService korisnikService;

    private KorisnikMapper korisnikMapper;

    public RegistracijaController() {
        this.korisnikMapper = new KorisnikMapper();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDto> registerAuthenticatedUser(@RequestBody KorisnikDto korisnikDto){
        Korisnik korisnik = korisnikMapper.toEntity(korisnikDto);
        try{
            korisnik = korisnikService.dodajNovogKorisnika(korisnik);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        korisnik.setLozinka(korisnikDto.getLozinka());
        return new ResponseEntity<>(korisnikMapper.toDto(korisnik), HttpStatus.ACCEPTED);
    }

}

