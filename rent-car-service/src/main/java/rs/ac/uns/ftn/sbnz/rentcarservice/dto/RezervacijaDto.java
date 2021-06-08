package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezervacijaDto {
    private int id;
    private LocalDate pocetakRezervacije;
    private LocalDate krajRezervacije;
    private String status;
    private int brojDana;
    private double iznos;
    private int autoId;
    private String autoMarka;
    private String autoModel;
    private String korisnikEmail;

    public RezervacijaDto(LocalDate pocetakRezervacije, LocalDate krajRezervacije, int autoId) {
        this.pocetakRezervacije = pocetakRezervacije;
        this.krajRezervacije = krajRezervacije;
        this.autoId = autoId;
    }
}
