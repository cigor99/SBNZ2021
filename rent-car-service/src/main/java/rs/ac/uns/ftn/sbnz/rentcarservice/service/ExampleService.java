package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.KieServices;


import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

@Service
public class ExampleService {
	private static Logger log = LoggerFactory.getLogger(ExampleService.class);

	@Autowired
	private KnowledgeService knowledgeService;

	public void tetsEvents() {

		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));
		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));
		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));
		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));
		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));
		knowledgeService.getEventsSession().insert(new ReviewEvent("mercedes"));

		knowledgeService.getEventsSession().fireAllRules();
		knowledgeService.releaseEventsSession();

	}


	public void testLoginEvents() {
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));
		knowledgeService.getEventsSession().insert(new LoginEvent("email@email.com"));

		knowledgeService.getEventsSession().fireAllRules();
		knowledgeService.releaseEventsSession();
	}

	public void testQuery() {
		List<Auto> atutomobili = getAllAutomobili();

		for(Auto a: atutomobili){
			knowledgeService.getRulesSession().insert(a);
		}

		QueryResults results = knowledgeService.getRulesSession().getQueryResults("pretrazi auto po imenu", "fer");

		for ( QueryResultsRow row : results ) {
			Auto auto = ( Auto ) row.get( "auto" );
			System.out.println( auto.getMarka().getNaziv() + "\n" );
		}
	}


	private List<Auto> getAllAutomobili(){
		Auto auto = new Auto(new Marka("tesla"), "model s", 2019, Karoserija.LIMUNZINA, TipGoriva.ELEKTRICNI, 4.5, 2.1, 1.3, 5,
				500, 0, 600, 3.5, 250, 45);

		Auto auto1 = new Auto(new Marka("ferskoda"), "oktavia", 2018, Karoserija.KARAVAN, TipGoriva.DIZEL, 6.5, 2.1, 1.3, 5,
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
