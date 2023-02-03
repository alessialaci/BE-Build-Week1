package it.epicodegruppo1.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.epicodegruppo1.entities.abstracts.TitoloDiViaggio;
import it.epicodegruppo1.enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "abbonamenti")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "check_validita_abbonamento", query = "UPDATE Abbonamento SET validita = false WHERE dataScadenza < CURRENT_DATE")
public class Abbonamento extends TitoloDiViaggio {

	@OneToOne(mappedBy = "abbonamento")
	private Utente utente;
	
	private boolean validita = true;
	
	@Enumerated(EnumType.STRING)
	private Periodicita durata;
	
	public Abbonamento(Utente utente, LocalDate dataEmissione, LocalDate dataScadenza, boolean validita, Periodicita durata) {
		super(dataEmissione, dataScadenza);
		this.utente = utente;
		this.validita = validita;
		this.durata = durata;
	}

}