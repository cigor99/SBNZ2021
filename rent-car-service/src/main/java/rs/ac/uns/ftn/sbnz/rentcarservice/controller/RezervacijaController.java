package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusRezervacije;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.RezervacijaService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.RezervacijaMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/rezervacija", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {

    @Autowired
    private RezervacijaService rezervacijaService;

    private final RezervacijaMapper rezervacijaMapper;

    public RezervacijaController() {
        this.rezervacijaMapper = new RezervacijaMapper();
    }

    @GetMapping()
    public ResponseEntity<List<RezervacijaDto>> sveRezervacije(){

        List<Rezervacija> rezervacije = rezervacijaService.findAll();

        rezervacije = rezervacije.stream().filter(rezervacija -> rezervacija.getStatus() == StatusRezervacije.KREIRANA).collect(Collectors.toList());

        return new ResponseEntity<>(rezervacijaMapper.toDtoList(rezervacije), HttpStatus.OK);

    }
}
