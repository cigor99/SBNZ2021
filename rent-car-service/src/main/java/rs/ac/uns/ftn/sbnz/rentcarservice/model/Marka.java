package rs.ac.uns.ftn.sbnz.rentcarservice.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Marka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String naziv;

    public Marka(String naziv){
        this.naziv = naziv;
    }
}
