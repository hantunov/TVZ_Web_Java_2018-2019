package hr.java.web.antunovic.moneyapp.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.java.web.antunovic.moneyapp.entities.Korisnik;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Repository
@Transactional
public class HibernateKorisnikRepository implements KorisnikRepository {
	
	private final EntityManager em;

	@Autowired
	public HibernateKorisnikRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Iterable<Korisnik> findAll() {
		try {
            return em
                    .createQuery("SELECT k FROM Korisnik k", Korisnik.class)
                    .getResultList();
        } catch (Exception ex) {
        	log.error("Ne postoji niti jedan korisnik!");
            return null;
        }
	}

	@Override
	public Korisnik findById(Long id) {
		try { 
			return em
					.createQuery("SELECT k FROM Korisnik k WHERE k.id = ?1", Korisnik.class)
					.setParameter(1, id).getSingleResult();
		} catch (Exception ex){
			log.error("Korisnik nije pronadjen u bazi!");
            return null;			
		}
	}
	
	@Override
	public Korisnik findByUsername(String username) {

		try { 
			return em
					.createQuery("SELECT k FROM Korisnik k WHERE k.username = :username", Korisnik.class)
					.setParameter("username", username).getSingleResult();
		} catch (Exception ex){
			log.error("Korisnik nije pronadjen u bazi!");
            return null;			
		}
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return em.merge(korisnik);
	}
}
