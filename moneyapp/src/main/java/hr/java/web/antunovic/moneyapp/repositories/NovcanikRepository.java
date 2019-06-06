package hr.java.web.antunovic.moneyapp.repositories;

import hr.java.web.antunovic.moneyapp.entities.Novcanik;

public interface NovcanikRepository {
	
	public Iterable<Novcanik> findAll();
	
	public Novcanik findOne(Long id);
	
	Novcanik save(Novcanik novcanik);
	
	Novcanik update(Novcanik novcanik);
	
	void delete(Long id);
	
}
