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


}
