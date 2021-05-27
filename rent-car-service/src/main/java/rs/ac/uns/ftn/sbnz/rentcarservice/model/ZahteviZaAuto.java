package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import lombok.Data;

@Data
public class ZahteviZaAuto {
	private Set<Karoserija> moguceKaroserije;
	private Set<TipGoriva> tipGoriva;
	private Set<DodatnaOprema> dodatneOpreme;
	private Set<DodaciZaUdobnost> dodaciZaUdobnost;
	
}
