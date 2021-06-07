package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.AutoRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AutoService {

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private KorisnikService korisnikService;

    public List<Auto> naprednaPretraga(KorisnickiUnosDto korisnickiUnosDto, Korisnik ulogovani){

        ZahteviZaAuto zza = new ZahteviZaAuto();
        List<Auto> atutomobili = autoRepository.findAll();
        Korisnik korisnik = korisnikService.findOneByEmail(ulogovani.getEmail());

//        knowledgeService.getRulesSession().insert(atutomobili);
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

        knowledgeService.getRulesSession().insert(zza);
        knowledgeService.getRulesSession().insert(korisnik);
        knowledgeService.getRulesSession().setGlobal("predlozeniAuti", predlozeniAuti);
        knowledgeService.getRulesSession().setGlobal("ulogovaniEmail", korisnik.getEmail());
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("rangiranje").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        predlozeniAuti = (ArrayList<Auto>) knowledgeService.getRulesSession().getGlobal("predlozeniAuti");

        knowledgeService.releaseRulesSession();
        System.out.println(predlozeniAuti);
        return predlozeniAuti;
    }


    public List<Auto> pretragaPoImenu(String containsString) {
        List<Auto> automobili = autoRepository.findAll();
        List<Auto> pronadjeniAutomobili = new ArrayList<>();

        for(Auto a: automobili){
            knowledgeService.getRulesSession().insert(a);
        }

        QueryResults results =  knowledgeService.getRulesSession().getQueryResults("pretrazi auto po imenu", containsString);

        for ( QueryResultsRow row : results ) {
            Auto auto = ( Auto ) row.get( "auto" );
            pronadjeniAutomobili.add(auto);
        }
        knowledgeService.releaseRulesSession();

        return pronadjeniAutomobili;
    }


    public Auto dodajNoviAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public Auto findOneById(int autoId) {
        return autoRepository.findById(autoId).orElse(null);
    }
}
