package it.epicodegruppo1.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.epicodegruppo1.entities.abstracts.Mezzo;
import it.epicodegruppo1.entities.abstracts.TitoloDiViaggio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "biglietti")
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends TitoloDiViaggio {

	private LocalDate vidimazione;
	private boolean timbrato = false;
	
	@OneToOne
	@JoinColumn(name = "mezzo_id", referencedColumnName = "mezzo_id")
	private Mezzo mezzo;
	
	public Biglietto(LocalDate dataEmissione, LocalDate dataScadenza, LocalDate vidimazione, boolean timbrato) {
		super(dataEmissione, dataScadenza);
		this.vidimazione = vidimazione;
		this.timbrato = timbrato;
	}
	
}