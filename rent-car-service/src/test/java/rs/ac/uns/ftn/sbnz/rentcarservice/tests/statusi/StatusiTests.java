package rs.ac.uns.ftn.sbnz.rentcarservice.tests.statusi;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import static org.junit.Assert.assertEquals;

public class StatusiTests {
    private KieSession kieSession;
    private Korisnik korisnik;

    @Before
    public void setUp(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("status").setFocus();
        korisnik = new Korisnik();

    }

    @Test
    public void Dodeli_bronzani(){
        for(int i=0;i<3;i++) {
            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setId(i);
            korisnik.getRezervacije().add(rezervacija);
        }
        kieSession.insert(korisnik);
        int rules = kieSession.fireAllRules();
        assertEquals(1, rules);
        assertEquals(StatusKorisnika.BRONZANI, korisnik.getStatus());
    }

    @Test
    public void Dodeli_srebrni(){
        for(int i=0;i<5;i++) {
            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setId(i);
            korisnik.getRezervacije().add(rezervacija);
        }
        kieSession.insert(korisnik);
        int rules = kieSession.fireAllRules();
        assertEquals(2, rules);
        assertEquals(StatusKorisnika.SREBRNI, korisnik.getStatus());
    }

    @Test
    public void Dodeli_zlatni(){
        for(int i=0;i<7;i++) {
            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setId(i);
            korisnik.getRezervacije().add(rezervacija);
        }
        kieSession.insert(korisnik);
        int rules = kieSession.fireAllRules();
        assertEquals(3, rules);
        assertEquals(StatusKorisnika.ZLATNI, korisnik.getStatus());
    }
}
