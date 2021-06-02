package rs.ac.uns.ftn.sbnz.rentcarservice.tests.popusti;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import rs.ac.uns.ftn.sbnz.rentcarservice.factory.FilterFactory;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.KnowledgeSessionHelper;

public class PopustiTests {
    private KieSession kieSession;

    private FilterFactory filterFactory;

    @Autowired
    public PopustiTests(){
        this.filterFactory = new FilterFactory();
    }

    @Before
    public void setup(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        kieSession = kieContainer.newKieSession("rulesSession");
    }

    @Test
    public void testIznajmljivanjeViseOdMesecDana(){

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);
        rezervacija.setBrojDana(54);

        kieSession.insert(rezervacija);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(95, rezervacija.getIznos(), 0);
    }

    @Test
    public void testIznajmljivanjeViseOdTriMeseca(){

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);
        rezervacija.setBrojDana(95);

        kieSession.insert(rezervacija);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(90, rezervacija.getIznos(), 0);
    }

    @Test 
    public void testIznajmljivanjeViseOdSestMeseci(){

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);
        rezervacija.setBrojDana(185);

        kieSession.insert(rezervacija);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(85, rezervacija.getIznos(), 0);
    }

    @Test
    public void testPopustBronzani(){

        Korisnik korisnik = new Korisnik();
        korisnik.setStatus(StatusKorisnika.BRONZANI);
        korisnik.setRezervacije(new HashSet<Rezervacija>());
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);

        kieSession.insert(rezervacija);
        kieSession.insert(korisnik);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(95, rezervacija.getIznos(), 0);
    }

    @Test
    public void testPopustSrebrni(){

        Korisnik korisnik = new Korisnik();
        korisnik.setStatus(StatusKorisnika.SREBRNI);
        korisnik.setRezervacije(new HashSet<Rezervacija>());
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);

        kieSession.insert(rezervacija);
        kieSession.insert(korisnik);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(90, rezervacija.getIznos(), 0);
    }

    @Test
    public void testPopustiZlatni(){

        Korisnik korisnik = new Korisnik();
        korisnik.setStatus(StatusKorisnika.ZLATNI);
        korisnik.setRezervacije(new HashSet<Rezervacija>());
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIznos(100);

        kieSession.insert(rezervacija);
        kieSession.insert(korisnik);
        kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(1, firedRules);
        assertEquals(85, rezervacija.getIznos(), 0);
    }
}
