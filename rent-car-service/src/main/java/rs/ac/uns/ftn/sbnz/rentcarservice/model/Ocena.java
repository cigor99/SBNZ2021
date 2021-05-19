package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.Data;

@Data
public class Ocena {
	private int id;
	private int vrednost;
	private Auto auto;
	private Korisnik korisnik;
}
