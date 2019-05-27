package hr.java.web.antunovic.moneyapp.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.java.web.antunovic.moneyapp.entities.Novcanik;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Repository
@Transactional
public class HibernateNovcanikRepository implements NovcanikRepository {

	private final EntityManager em;

	@Autowired
	public HibernateNovcanikRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Iterable<Novcanik> findAll() {
		try {
            return em
                    .createQuery("SELECT n FROM Novcanik n", Novcanik.class)
                    .getResultList();
        } catch (Exception ex) {
        	log.error("Ne postoji niti jedan novcanik!");
            return null;
        }
	}
	
	@Override
	public Novcanik findOne(Long id) {
		try { 
			return em
					.createQuery("SELECT n FROM Novcanik n WHERE n.id = ?1", Novcanik.class)
					.setParameter(1, id).getSingleResult();
		} catch (Exception ex){
			log.error("Novcanik nije pronadjen u bazi!");
            return null;			
		}
	}

	@Override
	public Novcanik save(Novcanik novcanik) {
		return em.merge(novcanik);
	}
}
