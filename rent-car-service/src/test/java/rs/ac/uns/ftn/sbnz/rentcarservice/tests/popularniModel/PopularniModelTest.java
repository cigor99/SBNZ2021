package rs.ac.uns.ftn.sbnz.rentcarservice.tests.popularniModel;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.AutoDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PopularniModelTest {
    private KieSession kieSession;
    private Auto auto;

    @Before
    public void setUp(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        ArrayList<Auto> predlozeniAuti = new ArrayList<>();
        kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.setGlobal("predlozeniAuti", predlozeniAuti);
        kieSession.getAgenda().getAgendaGroup("popularni-model").setFocus();
        auto = new Auto();
        auto.setMarka(new Marka("TEST AUTO"));
    }

    @Test
    public void popularniModelTest(){
        Set<Ocena> ocene = new HashSet<Ocena>();
        for(int i = 0; i<=10; i++){
            Ocena o = new Ocena();
            // o.setAuto(auto);
            o.setDatum(LocalDate.now());
            o.setVrednost(5);
            o.setId(i);
            ocene.add(o);
        }
        this.auto.setOcene(ocene);

        Auto noviAuto = new Auto();
        noviAuto.setMarka(new Marka("popularnija markaa"));

        Set<Ocena> ocene2 = new HashSet<Ocena>();
        for(int i = 0; i<=13; i++){
            Ocena o = new Ocena();
            // o.setAuto(auto);
            o.setDatum(LocalDate.now());
            o.setVrednost(5);
            o.setId(i);
            ocene2.add(o);
        }

        noviAuto.setOcene(ocene2);

        AutoDto popularniModel = new AutoDto();

        kieSession.setGlobal("popularniModel", popularniModel);
        kieSession.insert(this.auto);
        kieSession.insert(noviAuto);
        int rules = kieSession.fireAllRules();
        
        assertEquals(2, rules);
        assertEquals(popularniModel.getMarka(), noviAuto.getMarka().getNaziv());
    }
}
