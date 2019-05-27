package hr.java.web.antunovic.moneyapp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="novcanici")
@Data
public class Novcanik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	
	private LocalDateTime datum;
	
	private Long userID;
	
	@OneToMany(mappedBy = "novcanik")
	private List<Trosak> listaTroskova;
	
	public Novcanik() {
        listaTroskova = new ArrayList<>();
    }
	
	public BigDecimal izracunTroskova() {
		if(listaTroskova != null) {
			return listaTroskova.stream().map(trosak -> trosak.getIznos()).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(new BigDecimal(-1));
		} else {
			return BigDecimal.ZERO;
		}
	}
}