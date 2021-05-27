package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ZahteviZaAuto {
	private Set<Karoserija> moguceKaroserije;
	private Set<TipGoriva> tipGoriva;
	private Set<DodatnaOprema> dodatneOpreme;
	private Set<DodaciZaUdobnost> dodaciZaUdobnost;
	
	public ZahteviZaAuto() {
		this.moguceKaroserije = new HashSet<Karoserija>();
		this.tipGoriva = new HashSet<TipGoriva>();
		this.dodatneOpreme = new HashSet<DodatnaOprema>();
		this.dodaciZaUdobnost = new HashSet<DodaciZaUdobnost>();
	}
	
	
	
	
}
