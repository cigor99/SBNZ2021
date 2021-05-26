package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class DodatnaOprema {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String opis;
}
