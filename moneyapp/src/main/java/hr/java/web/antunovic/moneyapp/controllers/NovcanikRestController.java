package hr.java.web.antunovic.moneyapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.java.web.antunovic.moneyapp.entities.Novcanik;
import hr.java.web.antunovic.moneyapp.repositories.NovcanikRepository;

@RestController
@Secured("ROLE_USER")
@RequestMapping(path="/api/novcanik", produces="application/json")
@CrossOrigin
public class NovcanikRestController {
	
	@Autowired
	private NovcanikRepository novcanikRepository;
	
	public NovcanikRestController(NovcanikRepository novcanikRepository) {
		this.novcanikRepository = novcanikRepository;
	}
	
	@GetMapping public Iterable<Novcanik> findAll() {
		
		return novcanikRepository.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Novcanik>> findOne(@PathVariable Long id) {
		Optional<Novcanik> novcanik = novcanikRepository.findById(id);
		if(novcanik.isPresent()) {
			return new ResponseEntity<>(novcanik, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Novcanik save(@RequestBody Novcanik novcanik) {
		return novcanikRepository.save(novcanik);	
	}
	
	@PutMapping("/{id}")
	public Novcanik update(@RequestBody Novcanik novcanik) {		
		return novcanikRepository.save(novcanik);	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		novcanikRepository.deleteById(id);
	}
	
}
