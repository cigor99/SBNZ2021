package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.GeneratedValue;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ocena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int vrednost;

	@Column(nullable = false)
	private LocalDate datum;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;
}
