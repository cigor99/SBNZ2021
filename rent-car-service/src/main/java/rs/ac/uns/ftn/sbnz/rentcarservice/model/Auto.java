package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
public class Auto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "auto_id")
	private Marka marka;

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

	public Auto(Marka marka, String model, int godiste, Karoserija karoserija, TipGoriva tipGoriva,
				double duzina, double sirina, double visina, int brojSedista, int zapreminaGepeka,
				int zapreminaRezervoara, int distanca, double ubrzanje, int maksimalnaBrzina, double cena){

		this.marka = marka;
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
	
	public Auto() {
		this.dodatnaOprema = new HashSet<>();
		this.dodaciZaUdobnost = new HashSet<>();
	}

	public void setAuto(Auto a){
		this.marka = a.getMarka();
		this.bodovi = a.getBodovi();
		this.brojSedista = a.getBrojSedista();
		this.cena = a.getCena();
		this.model = a.getModel();
	}

	@Override
	public String toString() {
		return "Auto{" +
				"marka=" + marka +
				", model='" + model + '\'' +
				", godiste=" + godiste +
				", karoserija=" + karoserija +
				", tipGoriva=" + tipGoriva +
				", bodovi=" + bodovi +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Auto auto = (Auto) o;
		return id == auto.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
