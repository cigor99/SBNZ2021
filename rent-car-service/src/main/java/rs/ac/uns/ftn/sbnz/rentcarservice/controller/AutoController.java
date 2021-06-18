package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.AutoDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.jwt.TokenUtils;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.AutoService;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.AutoMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/auto", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoController {

    @Autowired
    private AutoService autoService;

    private AutoMapper autoMapper = new AutoMapper();

    @GetMapping("/{containsString}")
    public ResponseEntity<List<AutoDto>> pretragaPoImenu(@PathVariable("containsString") String containsString){
        List<Auto> pronadjeniAutomobili = autoService.pretragaPoImenu(containsString);
        List<AutoDto> autoDtoList = autoMapper.toDtoList(pronadjeniAutomobili);
        Collections.sort(autoDtoList);
        Collections.reverse(autoDtoList);
        return new ResponseEntity<>(autoDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/napredna-pretraga")
    public ResponseEntity<List<AutoDto>> naprednaPretraga(@RequestBody KorisnickiUnosDto korisnickiUnosDto){
        System.out.println(korisnickiUnosDto.getBudzet());
        List<AutoDto> autoDtoList;
        try{
            Korisnik korisnik = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Auto> pronadjeniAutomobili = autoService.naprednaPretraga(korisnickiUnosDto, korisnik);
            autoDtoList = autoMapper.toDtoList(pronadjeniAutomobili);
            Collections.sort(autoDtoList);
            Collections.reverse(autoDtoList);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(autoDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/dodavanje-auta")
    public ResponseEntity<AutoDto> dodajAuto(@RequestBody AutoDto autoDto){
        Auto auto = autoMapper.toEntity(autoDto);
        try{
            auto = autoService.dodajNoviAuto(auto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(autoMapper.toDto(auto), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<AutoDto>> sviAutomobili(){
        try{
            List<Auto> auti = autoService.findAll();
            return new ResponseEntity<>(autoMapper.toDtoList(auti), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }
}
