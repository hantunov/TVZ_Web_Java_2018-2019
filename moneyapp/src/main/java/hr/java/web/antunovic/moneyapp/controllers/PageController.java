package hr.java.web.antunovic.moneyapp.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import hr.java.web.antunovic.moneyapp.entities.Korisnik;
import hr.java.web.antunovic.moneyapp.entities.Novcanik;
import hr.java.web.antunovic.moneyapp.entities.Trosak;
import hr.java.web.antunovic.moneyapp.repositories.KorisnikRepository;
import hr.java.web.antunovic.moneyapp.repositories.NovcanikRepository;
import hr.java.web.antunovic.moneyapp.repositories.TrosakRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({"novcanik"})
public class PageController {
	
	TrosakRepository trosakRepository;
	NovcanikRepository novcanikRepository;
	KorisnikRepository korisnikRepository;	
	
	@Autowired
	public PageController(TrosakRepository trosakRepository, NovcanikRepository novcanikRepository,
			KorisnikRepository korisnikRepository) {
		this.trosakRepository = trosakRepository;
		this.novcanikRepository = novcanikRepository;
		this.korisnikRepository = korisnikRepository;
	}
	
	@ModelAttribute("novcanik")
	public Novcanik getNovcanik() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Korisnik loggedInUser = korisnikRepository.findByUsername(currentPrincipalName);
		
		log.info("Trenutni korisnik je {}, sa ID-jem: {}", currentPrincipalName, loggedInUser.getId());
		
		Novcanik novcanik = new Novcanik();
        novcanik.setUserID(loggedInUser.getId());
        novcanik.setDatum(LocalDateTime.now());
        novcanik.setNaziv(loggedInUser.getUsername() + "ov novèanik");
        novcanik = novcanikRepository.save(novcanik);

        return novcanik;
	}	
	
	@GetMapping("/")
	public String showHome(Model model) {	
		
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
		
		trosak.setNovcanik(novcanik);
		trosak.setDatum(LocalDateTime.now());
		trosak = trosakRepository.save(trosak);
		model.addAttribute("trosak", trosak);
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String danas = date.format(pattern);
		
		model.addAttribute("danas", danas);	
		
		trosakRepository.save(trosak);
		
		novcanik.getListaTroskova().add(trosak);
		
		return "PrikazTroska";
	}
	
	@GetMapping("/pretraga")
	public String showPretraga() {
	    return "PretragaTroska";
	}	
	
	@PostMapping("/pretraga/rezultat")
	public String showPretragaTroska(@RequestParam String naziv, Model model) {

		ArrayList<Trosak> troskovi = trosakRepository.findAllByNazivLike(naziv);
		model.addAttribute("troskovi", troskovi);		
		
	    return "PretragaTroska";
	}
	
	@GetMapping("/reset")
	public String resetWallet(SessionStatus status, @SessionAttribute("novcanik") Novcanik novcanik){
		novcanikRepository.save(novcanik);
		status.setComplete();
	return "redirect:/"; }
}
