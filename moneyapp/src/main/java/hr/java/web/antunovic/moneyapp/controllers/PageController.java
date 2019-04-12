package hr.java.web.antunovic.moneyapp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import hr.java.web.antunovic.moneyapp.entities.Novcanik;
import hr.java.web.antunovic.moneyapp.entities.Trosak;
import hr.java.web.antunovic.moneyapp.repositories.JdbcNovcanikRepository;
import hr.java.web.antunovic.moneyapp.repositories.JdbcTrosakRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({"novcanik"})
public class PageController {
	
	JdbcTrosakRepository jdbcTrosakRepository;
	JdbcNovcanikRepository jdbcNovcanikRepository;
	
	@Autowired
	public PageController(JdbcTrosakRepository jdbcTrosakRepository, JdbcNovcanikRepository jdbcNovcanikRepository) {
		this.jdbcTrosakRepository = jdbcTrosakRepository;
		this.jdbcNovcanikRepository = jdbcNovcanikRepository;
	}
	
	@ModelAttribute("novcanik")
	public Novcanik getNovcanik() {
		
		return new Novcanik();		
	}
	
	@GetMapping("")
	public String showHome() {
		
		return "Pocetna";
		
	}
	
	@GetMapping("/unos")
	public String showUnos(Model model) {
		model.addAttribute("trosak", new Trosak());
		model.addAttribute("vrsteTroska", Trosak.Vrsta.values());
		
		return "UnosTroska";
	}
	
	@PostMapping("/novi")
	public String showPrikazTroska(@Valid @ModelAttribute("trosak") Trosak trosak, @SessionAttribute("novcanik") Novcanik novcanik, Model model, Errors errors) {		
		
		if (errors.hasErrors()) {
			return "UnosTroska";
		}
		
		model.addAttribute("trosak", trosak);
		novcanik.setIme("Moja Gotovina");
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String danas = date.format(pattern);
		
		model.addAttribute("danas", danas);
		log.info("Obrada troska: " + trosak);
		
		jdbcTrosakRepository.save(trosak);
		
		novcanik.getListaTroskova().add(trosak);
		log.info("Obrada novcanika: " + novcanik);
		
		return "PrikazTroska";
	}
	
	@GetMapping("/reset")
	public String resetWallet(SessionStatus status, @SessionAttribute("novcanik") Novcanik novcanik){
		jdbcNovcanikRepository.save(novcanik);
		status.setComplete();
	return "redirect:"; }
}
