package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_utente")
	private long codUtente;
	
	private String nome;
	private String cognome;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "abbonamento_id")
	private Abbonamento abbonamento;
	
	private boolean abbonamentoattivo;
	
	public Utente(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}

	public boolean isAbbonamentoattivo() {
		return abbonamentoattivo;
	}

	public void setAbbonamentoattivo(boolean abbonamentoattivo) {
		this.abbonamentoattivo = abbonamentoattivo;
	}
	
}