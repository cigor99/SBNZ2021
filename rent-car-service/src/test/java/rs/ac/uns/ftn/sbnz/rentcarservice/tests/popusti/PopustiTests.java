package rs.ac.uns.ftn.sbnz.rentcarservice.tests.popusti;

import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import rs.ac.uns.ftn.sbnz.rentcarservice.factory.FilterFactory;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.KnowledgeSessionHelper;

public class PopustiTests {
    protected final String ksessionName = "filterKsession";

    private FilterFactory filterFactory;

    @Autowired
    public PopustiTests(){
        this.filterFactory = new FilterFactory();
    }

    @Test
    public void testIznajmljivanjeViseOdMesecDana(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

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
