package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.KieServices;


import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

@Service
public class ExampleService {
	private static Logger log = LoggerFactory.getLogger(ExampleService.class);

	private KnowledgeService knowledgeService;

	@Autowired
	public ExampleService(KnowledgeService knowledgeService) {
		log.info("Initialising a new example session.");
		this.knowledgeService = knowledgeService;
	}

	public void tetsEvents() {
		// KieSession eventSession = knowledgeService.getEventsSession();
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession eventSession = kc.newKieSession("eventsSession");

		eventSession.insert(new ReviewEvent(new Marka("mercedes")));
		eventSession.insert(new ReviewEvent(new Marka("mercedes")));
		eventSession.insert(new ReviewEvent(new Marka("mercedes")));
		eventSession.insert(new ReviewEvent(new Marka("mercedes")));
		eventSession.insert(new ReviewEvent(new Marka("mercedes")));

		eventSession.fireAllRules();

	}


}
