package entities.abstracts;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import entities.Distributore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticketing")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ticketing_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
abstract public class Ticketing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketing_id;
	
	private int bigliettiEmessi;
	private String luogo;
	private int counterBiglietti;
	private int abbonamentiEmessi;
	
	public Ticketing(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi) {
		this.bigliettiEmessi = bigliettiEmessi;
		this.luogo = luogo;
		this.counterBiglietti = counterBiglietti;
		this.abbonamentiEmessi = abbonamentiEmessi;
	}
	
}