package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Ocena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int vrednost;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;
}
