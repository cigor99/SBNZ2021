package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AutoService {

    @Autowired
    private KnowledgeService knowledgeService;

    public List<Auto> naprednaPretraga(KorisnickiUnosDto korisnickiUnosDto){

        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> atutomobili = new ArrayList<>();

        knowledgeService.getRulesSession().insert(atutomobili);
        knowledgeService.getRulesSession().insert(korisnickiUnosDto);
        knowledgeService.getRulesSession().insert(zza);
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("filter").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        knowledgeService.getRulesSession().dispose();
        knowledgeService.releaseRulesSession();

        ArrayList<Auto> predlozeniAuti = new ArrayList<>();

        for(Auto a: atutomobili){
            knowledgeService.getRulesSession().insert(a);
        }

        Korisnik korisnik = new Korisnik(1, "Ime", "Prezime", "email@email.com", "1234", StatusKorisnika.OBICNI, new HashSet<>(), new HashSet<>());

        Auto auto = new Auto();

        knowledgeService.getRulesSession().insert(zza);
        knowledgeService.getRulesSession().insert(korisnik);
        knowledgeService.getRulesSession().insert(auto);
        knowledgeService.getRulesSession().setGlobal("predlozeniAuti", predlozeniAuti);
        knowledgeService.getRulesSession().setGlobal("ulogovaniEmail", "email@email.com");
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("rangiranje").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        predlozeniAuti = (ArrayList<Auto>) knowledgeService.getRulesSession().getGlobal("predlozeniAuti");

        knowledgeService.releaseRulesSession();
        System.out.println(predlozeniAuti);
        return predlozeniAuti;
    }

}
