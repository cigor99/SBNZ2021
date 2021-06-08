package rs.ac.uns.ftn.sbnz.rentcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Authority;

import java.util.HashSet;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    HashSet<Authority> findByName(String name);
}
