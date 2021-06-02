package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
// @Expires("2h30m")
@Data
public class ReviewEvent implements Serializable{

    private static final long serialVersionUID = 1L;
    private Marka marka;
    private UUID id;
    private Date executionTime;

    public ReviewEvent(Marka marka){
        this.executionTime = new Date();
        this.marka = marka;
        this.id = UUID.randomUUID();
    }
}
