package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import lombok.Data;

@Data
public class Korisnik {
	private int id;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private StatusKorisnika status;
	private Set<Ocena> ocene;
	private Set<Rezervacija> rezervacije;

}
