package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends Osoba{
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "administrator_id")
	private Set<Rezervacija> odobreneRezervacije;

	public Administrator(int id, String ime, String prezime, String email, String lozinka, Set<Rezervacija> odobreneRezervacije) {
		super(id, ime, prezime, email, lozinka);
		this.odobreneRezervacije = odobreneRezervacije;
	}

	public Administrator() {
		this.odobreneRezervacije = new HashSet<>();
	}
}
