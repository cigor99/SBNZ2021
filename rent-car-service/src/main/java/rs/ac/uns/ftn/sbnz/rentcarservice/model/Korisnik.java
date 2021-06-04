package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("KORISNIK")
public class Korisnik extends Osoba{

    @Enumerated(EnumType.STRING)
	private StatusKorisnika status;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "korisnik")
	private Set<Ocena> ocene;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "korisnik_id")
	private Set<Rezervacija> rezervacije;

	public int getRezervacijaSize(){
		return this.rezervacije.size();
	}

	public Korisnik(){
		super();
		this.rezervacije = new HashSet<Rezervacija>();
	}

	public Korisnik(int id, String ime, String prezime, String email, String lozinka, StatusKorisnika status, Set<Ocena> ocene, Set<Rezervacija> rezervacije) {
		super(id, ime, prezime, email, lozinka);
		this.status = status;
		this.ocene = ocene;
		this.rezervacije = rezervacije;
	}

}
