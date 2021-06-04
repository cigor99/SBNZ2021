package rs.ac.uns.ftn.sbnz.rentcarservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Osoba;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.OsobaRepository;

@Service
public class SecureUserDetailsService implements UserDetailsService {

    @Autowired
    OsobaRepository osobaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws  UsernameNotFoundException {
        Osoba osoba = osobaRepository.findOneByEmail(email);

        if (osoba == null) {
            throw new UsernameNotFoundException(String.format("Nije pronadjen korisnik sa emailom: %s.", email));
        }

        return osoba;
    }
}
