package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class DodatnaOprema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String naziv;

    public DodatnaOprema(String naziv) {
        this.naziv = naziv;
    }
}
