package hr.java.web.antunovic.moneyapp.repositories;

import hr.java.web.antunovic.moneyapp.entities.Korisnik;

public interface KorisnikRepository {
		
	Iterable<Korisnik> findAll();

	Korisnik findById(Long id);
	
	Korisnik findByUsername(String username);
		
	Korisnik save(Korisnik korisnik);

}
