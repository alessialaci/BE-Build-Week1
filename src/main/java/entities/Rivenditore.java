package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.Ticketing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rivenditori")
@DiscriminatorValue("Rivenditore")
@Getter
@Setter
@NoArgsConstructor
public class Rivenditore extends Ticketing {
	
	public Rivenditore(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi) {
		super(bigliettiEmessi, luogo, counterBiglietti, abbonamentiEmessi);
	}
	
}