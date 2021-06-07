package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.RezervacijaRepository;

import java.util.List;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    List<Rezervacija> findAllByKorisnikId(Integer id){
        return  rezervacijaRepository.findAllByKorisnikId(id);
    }
}
