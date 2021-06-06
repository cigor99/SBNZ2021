package rs.ac.uns.ftn.sbnz.rentcarservice.tests.cep;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.KnowledgeService;
import static org.junit.Assert.assertEquals;

public class CEPTests {

    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void Popular_model_event(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));

        KieSession session = kContainer.newKieSession("eventsSession");

        session.insert(new ReviewEvent("mercedes"));
        session.insert(new ReviewEvent("mercedes"));
        session.insert(new ReviewEvent("mercedes"));
        session.insert(new ReviewEvent("mercedes"));
        session.insert(new ReviewEvent("mercedes"));
        session.insert(new ReviewEvent("mercedes"));

        int firedRules = session.fireAllRules();
        session.dispose();

        assertEquals(1, firedRules);
    }

    @Test
    public void Invalid_login_event(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));

        KieSession session = kContainer.newKieSession("eventsSession");

        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));
        session.insert(new LoginEvent("email@email.com"));

        int firedRules = session.fireAllRules();
        session.dispose();

        assertEquals(1, firedRules);

    }

    @Test
    public void Invalid_rezervacija_event(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));

        KieSession session = kContainer.newKieSession("eventsSession");

        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));
        session.insert(new RezervacijaEvent("email@email.com"));

        int firedRules = session.fireAllRules();
        session.dispose();

        assertEquals(1, firedRules);

    }
}
