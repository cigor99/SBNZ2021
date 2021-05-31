package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import lombok.Data;

@Entity
@Data
public class Auto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String naziv;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private int godiste;

	@Enumerated(EnumType.STRING)
	private Karoserija karoserija;

	@Enumerated(EnumType.STRING)
	private TipGoriva tipGoriva; // izmeni ordina na string

	@Column(nullable = false)
	private double duzina;

	@Column(nullable = false)
	private double sirina;

	@Column(nullable = false)
	private double visina;

	@Column(nullable = false)
	private int brojSedista;

	@Column(nullable = false)
	private int zapreminaGepeka;

	@Column(nullable = false)
	private int zapreminaRezervoara;

	@Column()
	private int distanca;

	@Column(nullable = false)
	private double ubrzanje;

	@Column(nullable = false)
	private int maksimalnaBrzina;

	@Column(nullable = false)
	private double cena;

	@Column()
	private double prosecnaOcena;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "auto_id")
	private Set<DodatnaOprema> dodatnaOprema;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "auto_id")
	private Set<DodatakZaUdobnost> dodaciZaUdobnost;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "auto")
	private Set<Ocena> ocene;

	private int bodovi = 0;

	public Auto(String naziv, String model, int godiste, Karoserija karoserija, TipGoriva tipGoriva,
				double duzina, double sirina, double visina, int brojSedista, int zapreminaGepeka,
				int zapreminaRezervoara, int distanca, double ubrzanje, int maksimalnaBrzina, double cena){

		this.naziv = naziv;
		this.model = model;
		this.godiste = godiste;
		this.karoserija = karoserija;
		this.tipGoriva = tipGoriva;
		this.duzina = duzina;
		this.sirina = sirina;
		this.visina = visina;
		this.brojSedista = brojSedista;
		this.zapreminaGepeka = zapreminaGepeka;
		this.zapreminaRezervoara = zapreminaRezervoara;
		this.distanca = distanca;
		this.ubrzanje = ubrzanje;
		this.maksimalnaBrzina = maksimalnaBrzina;
		this.cena = cena;
	}

	public Auto(Auto auto){

		this.naziv = auto.getNaziv();
		this.model = auto.getModel();
		this.godiste = auto.getGodiste();
		this.karoserija = auto.getKaroserija();
		this.tipGoriva = auto.getTipGoriva();
		this.duzina = auto.getDuzina();
		this.sirina = auto.getSirina();
		this.visina = auto.getVisina();
		this.brojSedista = auto.getBrojSedista();
		this.zapreminaGepeka = auto.getZapreminaGepeka();
		this.zapreminaRezervoara = auto.getZapreminaRezervoara();
		this.distanca = auto.getDistanca();
		this.ubrzanje = auto.getUbrzanje();
		this.maksimalnaBrzina = auto.getMaksimalnaBrzina();
		this.cena = auto.getCena();
		this.dodaciZaUdobnost = auto.getDodaciZaUdobnost();
		this.dodatnaOprema = auto.getDodatnaOprema();
	}
	
	public Auto() {
		this.dodatnaOprema = new HashSet<>();
		this.dodaciZaUdobnost = new HashSet<>();
	}

}
