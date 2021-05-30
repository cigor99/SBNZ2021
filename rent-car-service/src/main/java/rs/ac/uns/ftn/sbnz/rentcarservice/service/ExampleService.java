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

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

@Service
public class ExampleService {
	private static Logger log = LoggerFactory.getLogger(ExampleService.class);

	private final KieContainer kieContainer;

	@Autowired
	public ExampleService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

	public void test() {

		KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setEkoloskaVoznja(true);
		unos.setSvrha("DUZA_PUTOVANJA");
		unos.setBrojPutnika(4);
		unos.setBudzet("NISKI");
		Set<String> dodatnaOprema = new HashSet<>();
		dodatnaOprema.add("grejaci sedista");
		unos.setDodatnaOprema(dodatnaOprema);
		Set<String> dodaciZaUdobnost = new HashSet<String>();
		dodaciZaUdobnost.add("drzaci za case");
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

		Auto auto = new Auto("tesla", "model s", 2019, Karoserija.LIMUNZINA, TipGoriva.ELEKTRICNI, 4.5, 2.1, 1.3, 5,
				500, 0, 600, 3.5, 250, 8000);
		
		
		ArrayList<Auto> automobili = new ArrayList<Auto>();
		automobili.add(auto);
		ZahteviZaAuto zza = new ZahteviZaAuto();

		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(automobili);
		kieSession.insert(unos);
		kieSession.insert(zza);
		kieSession.getAgenda().getAgendaGroup("filter").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();

	}

	public void testKorisnik() {

		Korisnik korisnik = new Korisnik();
		HashSet<Rezervacija> rezervacije = new HashSet<>();
		for(int i=0;i<10;i++){
			Rezervacija rezervacija = new Rezervacija(i);
			rezervacije.add(rezervacija);
		}
		korisnik.setRezervacije(rezervacije);

		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(korisnik);
		kieSession.getAgenda().getAgendaGroup("status").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println(korisnik.getStatus());
	}

	public void testPopusti() {

		Korisnik korisnik = new Korisnik();
		HashSet<Rezervacija> rezervacije = new HashSet<>();
		for(int i=0;i<10;i++){
			Rezervacija rezervacija = new Rezervacija(i);
			rezervacije.add(rezervacija);
		}
		korisnik.setRezervacije(rezervacije);
		korisnik.setStatus(StatusKorisnika.SREBRNI);
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setBrojDana(181);
		rezervacija.setIznos(100);

		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(korisnik);
		kieSession.insert(rezervacija);
		kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println(rezervacija.getIznos());
	}


	public Auto getAuto(Auto a) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(a);
		kieSession.fireAllRules();
		kieSession.dispose();
		return a;
	}

}
