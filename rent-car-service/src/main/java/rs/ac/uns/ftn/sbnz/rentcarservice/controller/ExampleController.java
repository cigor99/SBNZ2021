package rs.ac.uns.ftn.sbnz.rentcarservice.controller;

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

	private final ExampleService exampleService;
	
	@Autowired
		public ExampleController(ExampleService exampleService) {
		this.exampleService = exampleService;
	}
	
	@RequestMapping(value = "/proba", method = RequestMethod.GET)
	public ResponseEntity<Void> example(){
		Auto auto = new Auto();
		auto.setName("auto");
		
		Auto a2 = exampleService.getAuto(auto);
		
		System.out.println(a2.getName());
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	};
}
