package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcenaDto {
    private int id;
    private int vrednost;
    private LocalDate datum;
    private int autoId;
    private String korisnikEmail;
}
