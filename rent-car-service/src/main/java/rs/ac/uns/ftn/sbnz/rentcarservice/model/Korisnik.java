package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Korisnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String lozinka;

    @Enumerated(EnumType.STRING)
	private StatusKorisnika status;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "korisnik")
	private Set<Ocena> ocene;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "korisnik_id")
	private Set<Rezervacija> rezervacije;

}
