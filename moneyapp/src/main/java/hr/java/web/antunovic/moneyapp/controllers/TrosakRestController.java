package hr.java.web.antunovic.moneyapp.controllers;

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

import hr.java.web.antunovic.moneyapp.entities.Trosak;
import hr.java.web.antunovic.moneyapp.repositories.HibernateTrosakRepository;

@RestController
@Secured("ROLE_USER")
@RequestMapping(path="/api/trosak", produces="application/json")
@CrossOrigin
public class TrosakRestController {
	
	@Autowired
	private final HibernateTrosakRepository trosakRepository;
	
	public TrosakRestController(HibernateTrosakRepository trosakRepository) {
		this.trosakRepository = trosakRepository;
	}
	
	@GetMapping public Iterable<Trosak> findAll() {
		
		return trosakRepository.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Trosak> findOne(@PathVariable Long id) {
		Trosak trosak = trosakRepository.findOne(id);
		if(trosak != null) {
			return new ResponseEntity<>(trosak, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Trosak save(@RequestBody Trosak trosak) {
		return trosakRepository.save(trosak);	
	}
	
	@PutMapping("/{id}")
	public Trosak update(@RequestBody Trosak trosak) {		
		return trosakRepository.update(trosak);		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		trosakRepository.delete(id);
	}
	
}