package rs.ac.uns.ftn.sbnz.rentcarservice.tests.rangiranje;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

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
        auto.setMarka(new Marka("TEST AUTO"));
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

    @Test
    public void Dodaj_birane_marke(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        Korisnik korisnik = new Korisnik(1, "Ime", "Prezime", "email@email.com", "1234", StatusKorisnika.OBICNI, new HashSet<>(), new HashSet<>());
        for(int i=0; i<20; i++){
            Auto auto = new Auto();
            auto.setMarka(new Marka("tesla"));
            Rezervacija rezervacija = new Rezervacija(i, auto);
            rezervacija.setPocetakRezervacije(LocalDate.now());
            korisnik.getRezervacije().add(rezervacija);
        }
        auto = new Auto();
        auto.setMarka(new Marka("tesla"));
        kieSession.insert(zza);
        kieSession.insert(korisnik);
        kieSession.insert(auto);
        kieSession.setGlobal("ulogovaniEmail", "email@email.com");

        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(1000, auto.getBodovi());
    }

    @Test
    public void Dodaj_mali_automobil(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setMaliAutomobil(true);
        auto.setBrojSedista(2);
        auto.setDuzina(3);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_duze_putovanje_sp_gepek(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setDuzePutovanjeSaPorodicom(true);
        auto.setZapreminaGepeka(500);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_duze_putovanje_sp_karoserija(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setDuzePutovanjeSaPorodicom(true);
        auto.setKaroserija(Karoserija.LIMUNZINA);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_sportski_automobil(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setSportskiAutomobil(true);
        auto.setUbrzanje(2);
        auto.setMaksimalnaBrzina(300);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_oldtajmer(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setOldtajmer(true);
        auto.setGodiste(1966);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_duze_eko(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        zza.setDuzeEkoPutovanje(true);
        auto.setDistanca(550);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }

    @Test
    public void Dodaj_sportska_sedista(){
        ZahteviZaAuto zza = new ZahteviZaAuto(zahteviZaAuto);
        Auto auto = new Auto();
        auto.setMarka(new Marka("TEST"));
        auto.getDodatnaOprema().add(new DodatnaOprema("sportska sedista"));
        auto.getDodatnaOprema().add(new DodatnaOprema("automatski menjac"));
        zza.setSportskiAutomobil(true);
        auto.setMaksimalnaBrzina(10);
        auto.setUbrzanje(10);
        kieSession.insert(zza);
        kieSession.insert(auto);
        int rules = kieSession.fireAllRules();

        assertEquals(2, rules);
        assertEquals(100, auto.getBodovi());
    }
}
