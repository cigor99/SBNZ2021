package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String lozinka;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "administrator_id")
	private Set<Rezervacija> odobreneRezervacije;
}
