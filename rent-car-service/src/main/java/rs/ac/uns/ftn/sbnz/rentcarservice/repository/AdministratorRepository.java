package rs.ac.uns.ftn.sbnz.rentcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}
