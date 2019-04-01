package hr.java.web.antunovic.moneyapp;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.DecimalMin;

@SuppressWarnings("serial")
public @Data class Trosak implements Serializable{
	
	@NotBlank(message="Naziv na moze biti prazan!")
	@Size(min=2, max=50)
	private String naziv;
	@NotNull(message="Iznos mora biti unesen!")
	@DecimalMin(value="0.01", message="Iznos mora biti veci od 0!")
	private BigDecimal iznos;
	@NotNull(message="Vrsta mora biti odabrana!")
	private Vrsta vrsta;
	
	public static enum Vrsta {
		Hrana, Piæe, Najam, Režije, Duæan
	}	
	
}