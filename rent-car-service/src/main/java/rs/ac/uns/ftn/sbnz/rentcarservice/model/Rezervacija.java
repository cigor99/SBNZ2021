package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Rezervacija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private Date pocetakRezervacije;

	@Column(nullable = false)
	private Date krajRezervacije;

    @Enumerated(EnumType.STRING)
	private StatusRezervacije status;

	@Column(nullable = false)
	private int brojDana;

	@Column(nullable = false)
	private double iznos;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	public Rezervacija(){}

	public Rezervacija(int id){this.id = id;}
	
}
