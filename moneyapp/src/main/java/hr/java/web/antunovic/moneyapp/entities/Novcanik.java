package hr.java.web.antunovic.moneyapp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Novcanik {
	private Long id;
	private LocalDateTime createDate;
	private String ime;
	private List<Trosak> listaTroskova = new ArrayList<Trosak>();
	
	public BigDecimal izracunTroskova() {
		if(listaTroskova != null) {
			return listaTroskova.stream().map(trosak -> trosak.getIznos()).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(new BigDecimal(-1));
		} else {
			return BigDecimal.ZERO;
		}
	}
}