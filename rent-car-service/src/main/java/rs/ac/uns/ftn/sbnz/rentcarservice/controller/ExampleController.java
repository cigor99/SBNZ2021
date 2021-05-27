package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.ExampleService;

@RestController
public class ExampleController {
	
	private static Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	private final ExampleService exampleService;
	
	@Autowired
		public ExampleController(ExampleService exampleService) {
		this.exampleService = exampleService;
	}
	
	@RequestMapping(value = "/proba", method = RequestMethod.GET)
	public ResponseEntity<Void> example(){
//		this.exampleService.test();
		// this.exampleService.testKorisnik();
		this.exampleService.testPopusti();
		return new ResponseEntity<Void>(HttpStatus.OK);
	};
}
