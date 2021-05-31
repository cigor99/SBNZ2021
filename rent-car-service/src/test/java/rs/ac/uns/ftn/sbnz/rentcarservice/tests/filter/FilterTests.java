package rs.ac.uns.ftn.sbnz.rentcarservice.tests.filter;

import java.util.List;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.factory.FilterFactory;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.util.KnowledgeSessionHelper;

public class FilterTests {
    
    protected final String ksessionName = "filterKsession";

    private FilterFactory filterFactory;

    @Autowired
    public FilterTests(){
        this.filterFactory = new FilterFactory();
    }

    @Test
    public void testEkoloskaVoznja(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        //ulazi u ruleEngine
        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviEkoloskiKorisnickiUnos();
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(2, zza.getTipGoriva().size());

        kieSession.dispose();
    }

    @Test 
    public void testDodatnaOprema(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviDodatnaOpremaUnos();
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(2, zza.getDodatneOpreme().size());

        assertEquals(korisnickiUnos.getDodatnaOprema(), zza.getDodatneOpreme());

        kieSession.dispose();
    }

    @Test
    public void testOpremaZaUdobnost(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.napraviOpremaZaUdobnost();
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(1, zza.getDodaciZaUdobnost().size());

        assertEquals(korisnickiUnos.getDodaciZaUdobnost(), zza.getDodaciZaUdobnost());

        kieSession.dispose();
    }

    @Test
    public void testUnosPutnika(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosPutniciIBudzet();
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(2, firedRules);
        
        assertEquals(korisnickiUnos.getBrojPutnika(), zza.getMinBrojSedista());

        kieSession.dispose();
    }

    @Test
    public void testBudzet(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosPutniciIBudzet();
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(2, firedRules);
        
        assertEquals(Budzet.SREDNJI, zza.getBudzet());

        kieSession.dispose();
    }
    
    @Test
    public void testSvrhaGradskaVoznja(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("GRADSKA_VOZNJA");
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 4);

        kieSession.dispose();
    }

    @Test
    public void testSvrhaDuzaPutovanja(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("DUZA_PUTOVANJA");
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 5);

        kieSession.dispose();
    }

    @Test
    public void testSvrhaZabava(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("ZABAVA");
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 3);

        kieSession.dispose();
    }

    @Test
    public void testSvrhaBiznis(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("BIZNIS");
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 3);

        kieSession.dispose();
    }

    @Test
    public void testSvrhaOffroad(){
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, ksessionName);

        KorisnickiUnosDto korisnickiUnos = filterFactory.korisnickiUnosSvrha("OFFROAD");
        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> automobili = filterFactory.getAllAutomobili();

        kieSession.insert(automobili);
        kieSession.insert(korisnickiUnos);
        kieSession.insert(zza);
        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(3, firedRules);
        
        assertEquals(zza.getMoguceKaroserije().size(), 2);

        kieSession.dispose();
    }
}
