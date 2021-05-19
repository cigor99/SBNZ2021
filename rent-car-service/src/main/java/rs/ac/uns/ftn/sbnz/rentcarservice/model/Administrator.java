package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import lombok.Data;

@Data
public class Administrator {
	private int id;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private Set<Rezervacija> odobreneRezervacije;
}
