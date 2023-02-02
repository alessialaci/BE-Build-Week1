package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import entities.abstracts.Mezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autobus")
@DiscriminatorValue("Autobus")
@Getter
@Setter
@NoArgsConstructor
public class Autobus extends Mezzo {
	
	public Autobus(int capienza, boolean inServizio, Tratta tratta, String numero) {
		super(capienza, inServizio, tratta, numero);
	}
	
}