package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Rezervacija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDate pocetakRezervacije;

	@Column(nullable = false)
	private LocalDate krajRezervacije;

    @Enumerated(EnumType.STRING)
	private StatusRezervacije status;

	@Column(nullable = false)
	private int brojDana;

	@Column(nullable = false)
	private double iznos;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	public Rezervacija(){}

	public Rezervacija(int id){this.id = id;}

	public Rezervacija(int id, Auto auto){this.id = id; this.auto = auto;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rezervacija that = (Rezervacija) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Rezervacija{" +
				"id=" + id +
				", pocetakRezervacije=" + pocetakRezervacije +
				", krajRezervacije=" + krajRezervacije +
				", status=" + status +
				", brojDana=" + brojDana +
				", iznos=" + iznos +
				'}';
	}
}
