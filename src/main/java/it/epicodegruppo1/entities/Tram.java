package it.epicodegruppo1.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.epicodegruppo1.entities.abstracts.Mezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tram")
@DiscriminatorValue("Tram")
@Getter
@Setter
@NoArgsConstructor
public class Tram extends Mezzo {

	public Tram(int capienza, boolean inServizio, Tratta tratta, String numero) {
		super(capienza, inServizio, tratta, numero);
	}
	
}