package entities;

import javax.persistence.Column;
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

	@Column(name = "in_servizio")
	private boolean inServizio;

	public Distributore(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi , boolean inServizio) {
		super(bigliettiEmessi, luogo, counterBiglietti, abbonamentiEmessi);
		this.inServizio = inServizio;
	}
	
}