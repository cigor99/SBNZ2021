package rs.ac.uns.ftn.sbnz.rentcarservice.model;

import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Data
@NoArgsConstructor
public class ReviewEvent implements Serializable{

    private static final long serialVersionUID = 1L;
    private String marka;
    private Date executionTime;
    private UUID id;

    public ReviewEvent(String marka){
        super();
        this.executionTime = new Date();
        this.marka = marka;
        this.id = UUID.randomUUID();
    }

}
