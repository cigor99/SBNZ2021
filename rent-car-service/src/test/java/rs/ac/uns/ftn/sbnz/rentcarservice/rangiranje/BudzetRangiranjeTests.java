package rs.ac.uns.ftn.sbnz.rentcarservice.rangiranje;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BudzetRangiranjeTests {
    private KieSession kieSession;
    private ZahteviZaAuto zahteviZaAuto;
    private Auto auto;

    @Before
    public void setUp(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        ArrayList<Auto> predlozeniAuti = new ArrayList<>();
        kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("predlozeniAuti", predlozeniAuti);
        kieSession.getAgenda().getAgendaGroup("rangiranje").setFocus();
        zahteviZaAuto = new ZahteviZaAuto(new HashSet<Karoserija>(), new HashSet<TipGoriva>(), 0, null, new HashSet<String>(), new HashSet<String>(), false, false, false, false, false );
        auto = new Auto();
        auto.setNaziv("TEST AUTO");
    }

    @Test
    public void Dodaj_3_nizak_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setBudzet(Budzet.NISKI);
        auto.setCena(19);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());
    }

    @Test
    public void Dodaj_1_nizak_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setBudzet(Budzet.NISKI);
        auto.setCena(21);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(1, auto.getBodovi());
    }

    @Test
    public void Dodaj_3_srednji_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        auto.setCena(21);
        zza.setBudzet(Budzet.SREDNJI);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());

    }

    @Test
    public void Dodaj_1_srednji_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        auto.setCena(19);
        zza.setBudzet(Budzet.SREDNJI);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(1, auto.getBodovi());

    }

    @Test
    public void Dodaj_3_visok_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        auto.setCena(61);
        zza.setBudzet(Budzet.VISOK);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());

    }

    @Test
    public void Dodaj_1_visok_budzet(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        auto.setCena(59);
        zza.setBudzet(Budzet.VISOK);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(1, auto.getBodovi());

    }

    @Test
    public void Dodaj_dodatna_oprema(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getDodatneOpreme().add("dodatak");
        auto.getDodatnaOprema().add(new DodatnaOprema("dodatak"));

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(2, auto.getBodovi());

    }

    @Test
    public void Dodaj_dodaci_za_udobnost(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getDodaciZaUdobnost().add("dodatak");
        auto.getDodaciZaUdobnost().add(new DodatakZaUdobnost("dodatak"));

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(2, auto.getBodovi());

    }

    @Test
    public void Dodaj_eko_voznja(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getTipGoriva().add(TipGoriva.ELEKTRICNI);
        auto.setTipGoriva(TipGoriva.ELEKTRICNI);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());
    }

    @Test
    public void Oduzmi_eko_voznja(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getTipGoriva().add(TipGoriva.ELEKTRICNI);
        auto.setTipGoriva(TipGoriva.DIZEL);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(-3, auto.getBodovi());
    }

    @Test
    public void Dodaj_karoserija(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getMoguceKaroserije().add(Karoserija.KUPE);
        auto.setKaroserija(Karoserija.KUPE);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());
    }

    @Test
    public void Oduzmi_karoserija(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.getMoguceKaroserije().add(Karoserija.KARAVAN);
        auto.setKaroserija(Karoserija.KUPE);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(-3, auto.getBodovi());
    }

    @Test
    public void Dodaj_broj_sedista(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setMinBrojSedista(4);
        auto.setBrojSedista(4);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(3, auto.getBodovi());
    }

    @Test
    public void Oduzmi_broj_sedista(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setMinBrojSedista(4);
        auto.setBrojSedista(2);

        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(-3, auto.getBodovi());
    }

}
