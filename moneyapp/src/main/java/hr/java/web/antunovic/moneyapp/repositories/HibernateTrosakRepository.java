package hr.java.web.antunovic.moneyapp.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.java.web.antunovic.moneyapp.entities.Trosak;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Repository
@Transactional
public class HibernateTrosakRepository implements TrosakRepository {

	private final EntityManager em;
	
	public HibernateTrosakRepository(EntityManager em) {
		this.em = em;
	}	
	
	@Override
	public Iterable<Trosak> findAll() {
		try {
			return em
	                .createQuery("SELECT t FROM Trosak t", Trosak.class)
	                .getResultList();
		} catch (Exception ex) {
			log.error("Ne postoji niti jedan trosak!");
			return null;
		}
	}

	@Override
	public Trosak findOne(Long id) {
		 try {
			 return em
	                .createQuery("SELECT t FROM Trosak t WHERE t.id = ?1", Trosak.class)
	                .setParameter(1, id)
	                .getSingleResult();
		 } catch (Exception ex) {
			 log.error("Trosak nije pronadjen u bazi!");
			 return null;
		}
	}

	@Override
	public Trosak save(Trosak trosak) {
		return em.merge(trosak);
	}
	
	@Override
	public Trosak update(Trosak trosak) {
		try {
			return em.merge(trosak);
		} catch (Exception ex) {
			log.error("Trosak nije promijenjen jer nije pronadjen u bazi!");
			return null;
		}
	}

	@Override	
	public void delete(Long id) {
		try {
			Trosak trosak = em
	                		.createQuery("SELECT t FROM Trosak t WHERE t.id = ?1", Trosak.class)
	                		.setParameter(1, id)
	                		.getSingleResult();
			em.remove(trosak);
		 } catch (Exception ex) {
			 log.error("Trosak nije obrisan jer nije pronadjen u bazi!");
		}
	}
	
}
