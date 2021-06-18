package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoDto implements Comparable<AutoDto>{
    private int id;
    private String marka;
    private String model;
    private int godiste;
    private String karoserija;
    private String tipGoriva;
    private double duzina;
    private double sirina;
    private double visina;
    private int brojSedista;
    private int zapreminaGepeka;
    private int zapreminaRezervoara;
    private int distanca;
    private double ubrzanje;
    private int maksimalnaBrzina;
    private double cena;
    private double prosecnaOcena;
    private Set<String> dodatnaOprema;
    private Set<String> dodaciZaUdobnost;
    private Integer bodovi;

    @Override
    public int compareTo(AutoDto a) {
        if (getBodovi() == null || a.getBodovi() == null) {
            return 0;
        }
        return this.getBodovi().compareTo(a.getBodovi());
    }
}
