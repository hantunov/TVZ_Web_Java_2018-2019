package hr.java.web.antunovic.moneyapp.repositories;

import hr.java.web.antunovic.moneyapp.entities.Trosak;

public interface TrosakRepository {
	
	Iterable<Trosak> findAll();

    Trosak findOne(Long id);
	
	Trosak save(Trosak trosak);
}