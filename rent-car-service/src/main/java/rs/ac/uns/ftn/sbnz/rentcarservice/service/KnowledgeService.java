package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeService {

	private final KieContainer kieContainer;
    private KieSession rulesSession;
    private KieSession eventsSession;

    public KnowledgeService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

  
    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public KieSession getRulesSession() {
        if (this.rulesSession == null) {
    	    rulesSession = kieContainer.newKieSession("rulesSession");
        }
        return rulesSession;
    }
    
    public void releaseRulesSession(){
        this.rulesSession.dispose();
        this.rulesSession = null;
    }
    
    public KieSession getEventsSession() {
        if(this.eventsSession == null){
    		this.eventsSession = kieContainer.newKieSession("eventsSession");
        }
        return this.eventsSession;
    }

    public void releaseEventsSession(){
        this.eventsSession.dispose();
        this.eventsSession = null;
    }



}
