package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Data
@NoArgsConstructor
public class RezervacijaEvent {
    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private String email;
    private UUID id;

    public RezervacijaEvent(String email){
        super();
        this.executionTime = new Date();
        this.id = UUID.randomUUID();
        this.email = email;
    }
}
