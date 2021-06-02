package rs.ac.uns.ftn.sbnz.rentcarservice.tests.filter;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.factory.FilterFactory;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.KnowledgeSessionHelper;

public class FilterTests {
    private KieSession kieSession;
    private ZahteviZaAuto zza;
    private KorisnickiUnosDto korisnickiUnosDto;

    @Autowired
    private FilterFactory filterFactory;

    public  FilterTests(){this.filterFactory = new FilterFactory();}

    @Before
    public void setUp(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));

        zza = new ZahteviZaAuto();
        kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
    }

    @Test
    public void testEkoloskaVoznja(){

        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviEkoloskiKorisnickiUnos();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(2, zza.getTipGoriva().size());
    }

    @Test 
    public void testDodatnaOprema(){
        
        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviDodatnaOpremaUnos();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(3, firedRules);
        assertEquals(2, zza.getDodatneOpreme().size());
        assertEquals(korisnickiUnos.getDodatnaOprema(), zza.getDodatneOpreme());
    }

    @Test
    public void testOpremaZaUdobnost(){
        
        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviOpremaZaUdobnost();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(1, zza.getDodaciZaUdobnost().size());
        assertEquals(korisnickiUnos.getDodaciZaUdobnost(), zza.getDodaciZaUdobnost());
    }

    @Test
    public void testUnosPutnika(){
        
        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosPutniciIBudzet();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(2, firedRules);
        
        assertEquals(korisnickiUnos.getBrojPutnika(), zza.getMinBrojSedista());
    }

    @Test
    public void testBudzet(){
        
        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosPutniciIBudzet();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(2, firedRules);
        
        assertEquals(Budzet.SREDNJI, zza.getBudzet());
    }
    
    @Test
    public void testSvrhaGradskaVoznja(){

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("GRADSKA_VOZNJA");
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 4);

    }

    @Test
    public void testSvrhaDuzaPutovanja(){

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("DUZA_PUTOVANJA");
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();

        assertEquals(3, firedRules);
        assertEquals(zza.getMoguceKaroserije().size(), 5);
    }

    @Test
    public void testSvrhaZabava(){

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("ZABAVA");
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        
        assertEquals(3, firedRules);
        assertEquals(zza.getMoguceKaroserije().size(), 3);
    }

    @Test
    public void testSvrhaBiznis(){

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("BIZNIS");
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 3);
    }

    @Test
    public void testSvrhaOffroad(){
        
        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("OFFROAD");
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 2);
    }

    @Test
    public void testGenMaliAutomobil(){
        KorisnickiUnosDto korisnickiUnos = getKorisnickiUnos("GRADSKA_VOZNJA", 2);
        
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        int firedRules = kieSession.fireAllRules();

        assertEquals(4, firedRules);
        assertTrue(zza.isMaliAutomobil());
    }

    @Test
    public void testGenDuzePutovanjePorodica(){
        KorisnickiUnosDto korisnickiUnos = getKorisnickiUnos("DUZE_PUTOVANJE", 4);

        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        int firedRules = kieSession.fireAllRules();

        assertEquals(3, firedRules);
        assertTrue(zza.isDuzePutovanjeSaPorodicom());
    }

    @Test
    public void testSportskiOldtajmer(){
        KorisnickiUnosDto korisnickiUnos = getKorisnickiUnos("ZABAVA", 2);

        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        int firedRules = kieSession.fireAllRules();

        assertEquals(4, firedRules);
        assertTrue(zza.isSportskiAutomobil());
        assertTrue(zza.isOldtajmer());
    }

    @Test
    public void testDuzeEko(){
        KorisnickiUnosDto korisnickiUnos = getKorisnickiUnos("DUZE_PUTOVANJE", 4);
        korisnickiUnos.setEkoloskaVoznja(true);

        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        int firedRules = kieSession.fireAllRules();

        assertEquals(5, firedRules);
        assertTrue(zza.isDuzeEkoPutovanje());
    }

    public KorisnickiUnosDto getKorisnickiUnos(String svrha, int brPutnika){
        KorisnickiUnosDto korisnickiUnos = new KorisnickiUnosDto();
        korisnickiUnos.setSvrha(svrha);
		korisnickiUnos.setBudzet("VISOK");
        korisnickiUnos.setBrojPutnika(brPutnika);
        korisnickiUnos.setDodaciZaUdobnost(new HashSet<String>());
        korisnickiUnos.setDodatnaOprema(new HashSet<String>());
        return korisnickiUnos;
    }
}
