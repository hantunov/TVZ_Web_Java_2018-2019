package hr.java.web.antunovic.moneyapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@GetMapping("/")
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
	public String showUnesenTrosakPage(Trosak trosak, Model model) {		
		
		model.addAttribute("trosak", trosak);
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String danas = date.format(pattern);
		
		model.addAttribute("danas", danas);
		
		return "PrikazTroska";
	}
}
