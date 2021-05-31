package rs.ac.uns.ftn.sbnz.rentcarservice.statusi;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class StatusiTests {
    private KieSession kieSession;
    private Korisnik korisnik;

    @Before
    public void setUp(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        ArrayList<Auto> predlozeniAuti = new ArrayList<>();
        kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("predlozeniAuti", predlozeniAuti);
        kieSession.getAgenda().getAgendaGroup("rangiranje").setFocus();
        Korisnik korisnik = new Korisnik();
        for(int i=0;i<5;i++) {
            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setId(i);
            korisnik.getRezervacije().add(rezervacija);
        }
    }

    @Test
    public void Dodeli_bronzani(){

    }
}
