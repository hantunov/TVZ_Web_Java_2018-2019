package hr.java.web.antunovic.moneyapp.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.java.web.antunovic.moneyapp.entities.Trosak;

@Repository
public class JdbcTrosakRepository implements TrosakRepository {

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert trosakInserter;

	@Autowired
	public JdbcTrosakRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.trosakInserter = new SimpleJdbcInsert(jdbc)
		.withTableName("troskovi")
		.usingGeneratedKeyColumns("id");
	}	

	@Override
	public Trosak save(Trosak trosak) {
		trosak.setDatum(LocalDateTime.now());
		trosak.setId(saveTrosakDetails(trosak));
		return trosak;
	}
	
	private long saveTrosakDetails(Trosak trosak) {
		Map<String, Object> values = new HashMap<>();
		values.put("naziv", trosak.getNaziv());
		values.put("iznos", trosak.getIznos());
		values.put("vrsta", trosak.getVrsta());
		values.put("datum", trosak.getDatum());
		return trosakInserter.executeAndReturnKey(values).longValue();
	}

	@Override
	public Iterable<Trosak> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trosak findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
