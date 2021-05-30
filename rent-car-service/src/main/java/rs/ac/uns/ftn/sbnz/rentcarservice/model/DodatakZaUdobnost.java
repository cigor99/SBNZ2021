package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class DodatakZaUdobnost{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String naziv;

	public DodatakZaUdobnost(String name){
		this.naziv = name;
	}
}