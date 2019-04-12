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
		this.novcanikInserter = new SimpleJdbcInsert(jdbc)
		.withTableName("novcanici")
		.usingGeneratedKeyColumns("id");
	}	

	@Override
	public Novcanik save(Novcanik novcanik) {
		novcanik.setCreateDate(LocalDateTime.now());
		novcanik.setId(saveNovcanikDetails(novcanik));
		return novcanik;
	}
	
	private long saveNovcanikDetails(Novcanik novcanik) {
		Map<String, Object> values = new HashMap<>();
		values.put("naziv", novcanik.getIme());
		values.put("datum", novcanik.getCreateDate());
		return novcanikInserter.executeAndReturnKey(values).longValue();
	}
}
