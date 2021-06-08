package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.DodatnaOprema;

@Data
public class KorisnickiUnosDto {
	private String svrha;
	private Set<String> dodatnaOprema;
	private Set<String> dodaciZaUdobnost;
	private int brojPutnika;
	private boolean ekoloskaVoznja;
	private String budzet;

	public int sizeOfDodatnaOprema(){
		return dodatnaOprema.size();
	}
	public int sizeOfDodaciZaUdobnost(){
		return dodaciZaUdobnost.size();
	}

	public KorisnickiUnosDto(String svrha, Set<String> dodatnaOprema, Set<String> dodaciZaUdobnost, int brojPutnika, boolean ekoloskaVoznja, String budzet) {
		this.svrha = svrha;
		this.dodatnaOprema = dodatnaOprema;
		this.dodaciZaUdobnost = dodaciZaUdobnost;
		this.brojPutnika = brojPutnika;
		this.ekoloskaVoznja = ekoloskaVoznja;
		this.budzet = budzet;
	}

	public KorisnickiUnosDto(String svrha, int brojPutnika, boolean ekoloskaVoznja, String budzet) {
		this.svrha = svrha;
		this.dodatnaOprema = new HashSet<>();
		this.dodaciZaUdobnost = new HashSet<>();
		this.brojPutnika = brojPutnika;
		this.ekoloskaVoznja = ekoloskaVoznja;
		this.budzet = budzet;
	}

	public KorisnickiUnosDto() {
		this.dodatnaOprema = new HashSet<>();
		this.dodaciZaUdobnost = new HashSet<>();
	}
}
