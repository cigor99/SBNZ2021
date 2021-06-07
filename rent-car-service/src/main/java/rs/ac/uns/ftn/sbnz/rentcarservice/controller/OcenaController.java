package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.OcenaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.OcenaService;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "api/auto", produces = MediaType.APPLICATION_JSON_VALUE)

public class OcenaController {

//    @Autowired
//    private OcenaService ocenaService;

//    @PostMapping()
//    public ResponseEntity<Void> oceniAuto(@RequestBody OcenaDto ocenaDto){
//        Ocena
//    }
}
