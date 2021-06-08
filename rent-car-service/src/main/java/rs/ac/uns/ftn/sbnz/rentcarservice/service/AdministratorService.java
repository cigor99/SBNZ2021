package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Administrator;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.AdministratorRepository;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator findOneByEmail(String email){
        Administrator administrator = administratorRepository.findOneByEmail(email);
        if(administrator == null)
            throw new UsernameNotFoundException(String.format("Nije pronadjen administrator sa emailom: %s.", email));
        return administrator;
    }

    public Administrator updateAdministrator(Administrator administrator){
        return administratorRepository.save(administrator);
    }
}
