package entities.abstracts;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entities.Tratta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "mezzi_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
abstract public class Mezzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mezzo_id;
	
	private int capienza;
	
	@Column(name = "in_servizio")
	private boolean inServizio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tratte_id", referencedColumnName = "tratta_id")
	private Tratta tratta;
	
	private String numero;
	
	@Column(name = "biglietti_vidimati")
	private int bigliettiVidimati;

	public Mezzo(int capienza, boolean inServizio, Tratta tratta, String numero) {
		this.capienza = capienza;
		this.inServizio = inServizio;
		this.tratta = tratta;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase() + " " + numero + " - Partenza: " + tratta.getPartenza() + " - Arrivo: " + tratta.getCapolinea();
	}
	
}