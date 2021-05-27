package rs.ac.uns.ftn.sbnz.rentcarservice.dto;

import java.util.Set;

import lombok.Data;

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
}
