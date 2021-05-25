package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import lombok.Data;

@Data
public class Auto {
	private int id;
	private String naziv;
	private String model;
	private int godiste;
	private Karoserija karoserija;
	private TipGoriva tipGoriva;
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
	private Set<DodatnaOprema> dodatnaOprema;
	private Set<DodaciZaUdobnost> dodaciZaUdobnost;
	private Set<Ocena> ocene;

}
