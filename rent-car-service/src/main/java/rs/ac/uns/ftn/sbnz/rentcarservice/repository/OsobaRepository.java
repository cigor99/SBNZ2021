package rs.ac.uns.ftn.sbnz.rentcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Osoba;

import java.util.List;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Integer> {
    List<Osoba> findAll();

    Osoba findOneById(Long id);

    Osoba findOneByEmail(String email);
}
