package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
	
@Data
@AllArgsConstructor
public class ZahteviZaAuto {
	private Set<Karoserija> moguceKaroserije;
	private Set<TipGoriva> tipGoriva;
	private int minBrojSedista;
	private Budzet budzet;
	private Set<String> dodatneOpreme;
	private Set<String> dodaciZaUdobnost;

	private boolean maliAutomobil;
	private boolean duzePutovanjeSaPorodicom;
	private boolean sportskiAutomobil;
	private boolean oldtajmer;
	private boolean duzeEkoPutovanje;
	
	public ZahteviZaAuto() {
		this.moguceKaroserije = new HashSet<Karoserija>();
		this.tipGoriva = new HashSet<TipGoriva>();
		this.dodatneOpreme = new HashSet<String>();
		this.dodaciZaUdobnost = new HashSet<String>();
	}

	public ZahteviZaAuto(ZahteviZaAuto zahtev){
		this.moguceKaroserije = zahtev.getMoguceKaroserije();
		this.tipGoriva = zahtev.getTipGoriva();
		this.dodatneOpreme = zahtev.getDodatneOpreme();
		this.dodaciZaUdobnost = zahtev.getDodaciZaUdobnost();
		this.minBrojSedista = zahtev.getMinBrojSedista();
		this.budzet = zahtev.getBudzet();
		this.maliAutomobil = zahtev.isMaliAutomobil();
		this.duzeEkoPutovanje = zahtev.isDuzeEkoPutovanje();
		this.duzePutovanjeSaPorodicom = zahtev.isDuzePutovanjeSaPorodicom();
		this.sportskiAutomobil = zahtev.isSportskiAutomobil();
		this.oldtajmer = zahtev.isOldtajmer();
	}

}
