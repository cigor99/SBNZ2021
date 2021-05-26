package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class DodaciZaUdobnost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = true)
	private String opis;
}
