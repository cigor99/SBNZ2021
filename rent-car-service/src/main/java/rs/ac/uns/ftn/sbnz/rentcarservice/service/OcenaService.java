package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Ocena;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.OcenaRepository;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    public Ocena oceniAuto(Ocena ocena){
        return ocenaRepository.save(ocena);
    }

}
