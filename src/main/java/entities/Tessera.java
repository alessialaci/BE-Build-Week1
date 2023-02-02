package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tessere")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "check_validita", query = "UPDATE Tessera SET validita = false WHERE dataScadenza < CURRENT_DATE")
public class Tessera extends Utente {

	private LocalDate dataEmissione;
	private LocalDate dataScadenza;
	private boolean validita = true;
	
	public Tessera(String nome, String cognome, String email, LocalDate dataEmissione, LocalDate dataScadenza) {
		super(nome, cognome, email);
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
	}

	public boolean isValidita() {
		return validita;
	}

	public void setValidita(boolean validita) {
		this.validita = validita;
	}
	
}