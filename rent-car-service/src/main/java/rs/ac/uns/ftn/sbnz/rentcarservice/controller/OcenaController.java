package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.OcenaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.NepostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Ocena;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.RezervacijaRepository;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.AutoService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.OcenaService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.RezervacijaService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.OcenaMapper;

import java.time.LocalDate;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/ocena", produces = MediaType.APPLICATION_JSON_VALUE)

public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    private final OcenaMapper ocenaMapper;

    public OcenaController() {
        this.ocenaMapper = new OcenaMapper();
    }

    @PostMapping(value="/{rezervacijaId}")
    public ResponseEntity<OcenaDto> oceniAuto(@RequestBody OcenaDto ocenaDto, @PathVariable Integer rezervacijaId) throws Exception {
        Korisnik korisnik = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Auto auto =  autoService.findOneById(ocenaDto.getAutoId());
        Rezervacija r = rezervacijaRepository.findById(rezervacijaId).orElseThrow(RuntimeException::new);
        r.setOcena(ocenaDto.getVrednost());
        rezervacijaRepository.save(r);

        if(auto == null)
            throw new NepostojeciObjekatException("auto", "id");
        Ocena ocena = new Ocena(0, ocenaDto.getVrednost(), LocalDate.now(), auto, korisnik);
        try{
            ocena = ocenaService.oceniAuto(ocena);
            return new ResponseEntity<>(ocenaMapper.toDto(ocena), HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
