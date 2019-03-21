package hr.java.web.antunovic.moneyapp;

import java.io.Serializable;
import lombok.Data;

@SuppressWarnings("serial")
public @Data class Trosak implements Serializable{
	
	private String naziv;
	private Double iznos;
	private Vrsta vrsta;
	
	public static enum Vrsta {
		Hrana, Piæe, Najam, Režije, Duæan
	}	
	
}