package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.KorisnickiUnosDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.AutoService;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.ExampleService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ExampleController {
	
	private static Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	private final ExampleService exampleService;
	private final AutoService autoService;

	@Autowired
		public ExampleController(ExampleService exampleService, AutoService autoService) {
		this.exampleService = exampleService;
		this.autoService = autoService;
	}
	
	@RequestMapping(value = "/proba", method = RequestMethod.GET)
	public ResponseEntity<Void> example(){
//		this.exampleService.test();
		// this.exampleService.testKorisnik();
//		this.exampleService.testPopusti();

		KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setEkoloskaVoznja(true);
		unos.setSvrha("DUZA_PUTOVANJA");
		unos.setBrojPutnika(4);
		unos.setBudzet("NISKI");
		Set<String> dodatnaOprema = new HashSet<String>();
		dodatnaOprema.add("grejaci sedista");
		unos.setDodatnaOprema(dodatnaOprema);
		Set<String> dodaciZaUdobnost = new HashSet<String>();
		dodaciZaUdobnost.add("drzaci za case");
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

		autoService.naprednaPretraga(unos);
		return new ResponseEntity<Void>(HttpStatus.OK);
	};
}
