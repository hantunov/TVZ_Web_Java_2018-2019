package hr.java.web.antunovic.moneyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.java.web.antunovic.moneyapp.entities.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{	
	Korisnik findByUsername(String username);	
}
