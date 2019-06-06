package hr.java.web.antunovic.moneyapp.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.java.web.antunovic.moneyapp.entities.Novcanik;

@Repository
public class JdbcNovcanikRepository implements NovcanikRepository {

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert novcanikInserter;

	@Autowired
	public JdbcNovcanikRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.novcanikInserter = new SimpleJdbcInsert(jdbc).withTableName("novcanici").usingGeneratedKeyColumns("id");
	}
	
	@Override
	public Iterable<Novcanik> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Novcanik findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Novcanik save(Novcanik novcanik) {
		novcanik.setDatum(LocalDateTime.now());
		novcanik.setId(saveNovcanikDetails(novcanik));
		return novcanik;
	}
	
	public long saveNovcanikDetails(Novcanik novcanik) {
		Map<String, Object> values = new HashMap<>();
		values.put("naziv", novcanik.getNaziv());
		values.put("datum", novcanik.getDatum());
		return novcanikInserter.executeAndReturnKey(values).longValue();
	}

	@Override
	public Novcanik update(Novcanik novcanik) {
		// TODO Auto-generated method stub
		return novcanik;	
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
