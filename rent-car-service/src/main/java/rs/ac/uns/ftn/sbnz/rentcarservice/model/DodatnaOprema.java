package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
