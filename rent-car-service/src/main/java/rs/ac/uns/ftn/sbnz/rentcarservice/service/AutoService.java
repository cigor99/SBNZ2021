package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AutoService {

    private final KieContainer kieContainer;

//    @Autowired
//    private AutoRepository autoRepository;

    @Autowired private KnowledgeService knowledgeService;


    @Autowired
    public AutoService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Auto> naprednaPretraga(KorisnickiUnosDto korisnickiUnosDto){

        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> atutomobili = getAllAutomobili();

        knowledgeService.getRulesSession().insert(atutomobili);
        knowledgeService.getRulesSession().insert(korisnickiUnosDto);
        knowledgeService.getRulesSession().insert(zza);
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("filter").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        knowledgeService.getRulesSession().dispose();
        knowledgeService.releaseRulesSession();
//
        ArrayList<Auto> predlozeniAuti = new ArrayList<>();

        for(Auto a: atutomobili){
            knowledgeService.getRulesSession().insert(a);
        }

        Korisnik korisnik = new Korisnik(1, "Ime", "Prezime", "email@email.com", "1234", StatusKorisnika.OBICNI, new HashSet<>(), new HashSet<>());
        for(int i=0; i<20; i++){
            Auto auto = new Auto();
            auto.setMarka(new Marka("tesla"));
            Rezervacija rezervacija = new Rezervacija(i, auto);
            rezervacija.setPocetakRezervacije(LocalDate.now());
            korisnik.getRezervacije().add(rezervacija);
        }

        knowledgeService.getRulesSession().insert(zza);
        knowledgeService.getRulesSession().insert(korisnik);
        knowledgeService.getRulesSession().setGlobal("predlozeniAuti", predlozeniAuti);
        knowledgeService.getRulesSession().setGlobal("ulogovaniEmail", "email@email.com");
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("rangiranje").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        predlozeniAuti = (ArrayList<Auto>) knowledgeService.getRulesSession().getGlobal("predlozeniAuti");

        knowledgeService.releaseRulesSession();
        System.out.println(predlozeniAuti);
        return predlozeniAuti;
    }

    private List<Auto> getAllAutomobili(){
        Auto auto = new Auto(new Marka("tesla"), "model s", 2019, Karoserija.LIMUNZINA, TipGoriva.ELEKTRICNI, 4.5, 2.1, 1.3, 5,
                500, 0, 600, 3.5, 250, 45);

        Auto auto1 = new Auto(new Marka("skoda"), "oktavia", 2018, Karoserija.KARAVAN, TipGoriva.DIZEL, 6.5, 2.1, 1.3, 5,
                500, 600, 0, 3.5, 220, 15);

        Auto auto2 = new Auto(new Marka("smart"), "two", 2018, Karoserija.KUPE, TipGoriva.ELEKTRICNI, 3, 2.1, 1.3, 2,
                40, 300, 0, 3.5, 220, 15);

        Auto auto3 = new Auto(new Marka("ferarri"), "488 pista", 2018, Karoserija.KUPE, TipGoriva.BENZIN, 3, 2.1, 1.3, 2,
            40, 300, 0, 2.8, 340, 15);

        HashSet<DodatnaOprema> dodatnaOprema = new HashSet<>();
        dodatnaOprema.add(new DodatnaOprema("grejaci sedista"));
        dodatnaOprema.add(new DodatnaOprema("automatski menjac"));
        HashSet<DodatakZaUdobnost> udobnost = new HashSet<>();
        udobnost.add(new DodatakZaUdobnost("drzaci za case"));

        HashSet<DodatnaOprema> dodatnaOprema2 = new HashSet<>();
        dodatnaOprema2.add(new DodatnaOprema("grejaci sedista"));
        HashSet<DodatakZaUdobnost> udobnost2 = new HashSet<>();
        udobnost2.add(new DodatakZaUdobnost("drzaci za case"));

        auto.setDodatnaOprema(dodatnaOprema);
        auto.setDodaciZaUdobnost(udobnost);

        auto1.setDodaciZaUdobnost(udobnost2);
        auto1.setDodatnaOprema(dodatnaOprema2);

        auto2.setDodatnaOprema(dodatnaOprema);
        auto2.setDodaciZaUdobnost(udobnost);

        dodatnaOprema.add(new DodatnaOprema("sportska sedista"));
        auto3.setDodatnaOprema(dodatnaOprema);

        ArrayList<Auto> automobili = new ArrayList<Auto>();
        automobili.add(auto);
        automobili.add(auto1);
        automobili.add(auto2);
        automobili.add(auto3);
        return automobili;
    }


}
