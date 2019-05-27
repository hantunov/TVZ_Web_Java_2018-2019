package hr.java.web.antunovic.moneyapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="troskovi")
@Data
@NoArgsConstructor
public class Trosak implements Serializable{
	
	private static final long serialVersionUID = -3823560556689189126L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotBlank(message="Naziv na moze biti prazan!")
	@Size(min=2, max=50)
	private String naziv;
	
	@NotNull(message="Iznos mora biti unesen!")
	@DecimalMin(value="0.01", message="Iznos mora biti veci od 0!")
	private BigDecimal iznos;
	
	@NotNull(message="Vrsta mora biti odabrana!")
	private Vrsta vrsta;
	
	private LocalDateTime datum;
	
	@ManyToOne
    @JoinColumn(name = "NOVCANIKID")
	private Novcanik novcanik;
	
	public static enum Vrsta {
		Hrana, Piæe, Najam, Režije, Duæan
	}
	
}