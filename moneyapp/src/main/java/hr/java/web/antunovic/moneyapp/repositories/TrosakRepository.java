package hr.java.web.antunovic.moneyapp.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import hr.java.web.antunovic.moneyapp.entities.Trosak;

public interface TrosakRepository extends CrudRepository<Trosak, Long>{

	ArrayList<Trosak> findAllByNaziv(String naziv);

	ArrayList<Trosak> findAllByNazivLike(String naziv);

}