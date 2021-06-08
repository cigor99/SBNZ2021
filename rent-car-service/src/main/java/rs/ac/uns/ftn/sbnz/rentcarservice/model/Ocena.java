package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.GeneratedValue;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ocena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int vrednost;

	@Column(nullable = false)
	private LocalDate datum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ocena ocena = (Ocena) o;
		return id == ocena.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
