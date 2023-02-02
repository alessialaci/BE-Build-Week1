package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.Ticketing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "distributori")
@DiscriminatorValue("Distributore")
@Getter
@Setter
@NoArgsConstructor
public class Distributore extends Ticketing {

	private boolean inServizio;

	public Distributore(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi , boolean inServizio) {
		super(bigliettiEmessi, luogo, counterBiglietti, abbonamentiEmessi);
		this.inServizio = inServizio;
	}
	
}