package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auto {
	private String name;
	private String model;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
}
