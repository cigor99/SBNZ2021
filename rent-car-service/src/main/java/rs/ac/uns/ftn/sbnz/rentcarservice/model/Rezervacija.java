package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class Rezervacija {
	private int id;
	private Date pocetakRezervacije;
	private Date krajRezervacije;
	private StatusRezervacije status;
	private int brojDana;
	private double iznos;
	private Auto auto;
	private Korisnik korisnik;
	
}
