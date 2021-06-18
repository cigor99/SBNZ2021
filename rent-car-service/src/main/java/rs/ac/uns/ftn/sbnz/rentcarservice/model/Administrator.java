package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Osoba{

	@Transient
	private static final long serialVersionUID = 1L;
	
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
