package rs.ac.uns.ftn.sbnz.rentcarservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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
