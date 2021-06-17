package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.NepostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Administrator;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.RezervacijaService;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/administrator", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdministratorController {

    @Autowired
    private RezervacijaService rezervacijaService;

    @GetMapping("/odbij-rezervaciju/{rezervacijaId}")
    public ResponseEntity<Void> odbijRezervaciju(@PathVariable Integer rezervacijaId) {
        try {
            Administrator ulogovani = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            rezervacijaService.odbijRezervaciju(rezervacijaId, ulogovani);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/odobri-rezervaciju/{rezervacijaId}")
    public ResponseEntity<Void> odobriRezervaciju(@PathVariable Integer rezervacijaId) {
        try {
            Administrator ulogovani = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            rezervacijaService.odobriRezervaciju(rezervacijaId, ulogovani);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
