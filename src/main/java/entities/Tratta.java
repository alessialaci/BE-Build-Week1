package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entities.abstracts.Mezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tratte")
@Getter
@Setter
@NoArgsConstructor
public class Tratta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tratta_id;

	private String partenza;
	private String capolinea;
	private int tempoPercorrenza;
	
	@OneToMany(mappedBy = "tratta")
	private Set<Mezzo> mezzi;

	public Tratta(String partenza, String capolinea, int tempoPercorrenza) {
		super();
		this.partenza = partenza;
		this.capolinea = capolinea;
		this.tempoPercorrenza = tempoPercorrenza;
	}
	
}