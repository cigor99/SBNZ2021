package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Ocena;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.ReviewEvent;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.OcenaRepository;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;


    @Autowired
    private KnowledgeService knowledgeService;

    public Ocena oceniAuto(Ocena ocena){

        Ocena sacuvana = ocenaRepository.save(ocena);
        knowledgeService.getEventsSession().insert(new ReviewEvent(sacuvana.getAuto().getMarka().getNaziv()));
        knowledgeService.getEventsSession().fireAllRules();
        return sacuvana;
    }

}
