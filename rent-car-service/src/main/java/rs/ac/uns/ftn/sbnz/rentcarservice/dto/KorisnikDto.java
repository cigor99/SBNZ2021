package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikDto {

    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String status;

}
