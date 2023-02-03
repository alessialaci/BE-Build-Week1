package it.epicodegruppo1.dao;

import java.util.Random;

import it.epicodegruppo1.entities.Tratta;
import it.epicodegruppo1.entities.abstracts.Mezzo;
import it.epicodegruppo1.utils.JpaUtils;


public class TrattaDAO extends JpaUtils {

	// METODO PER SALVARE LA TRATTA SUL DATABASE
	public void save(Tratta tr) {
		try {
			t.begin();
			em.persist(tr);
			t.commit();
			
			System.out.println("Tratta inserita correttamente");
		} catch(Exception e) {
			logger.error("Errore durante l'inserimento della tratta " + e);
		}
	}
	
	
	// METODO PER GENERARE IL TEMPO DI PERCORRENZA EFFETTIVA DELLE TRATTE
	public static void tempoEffettivo(int id) {
		Mezzo m = em.find(Mezzo.class, id);
		
		int number = m.getTratta().getTempoPercorrenza();
	    int range = 5;
	    Random rand = new Random();
	    int randomNumber = rand.nextInt(range * 2 + 1) - range;
	    int result = number + randomNumber;

		System.out.println("Hai effettuato la corsa " + m.getTratta().getPartenza() + " - " + m.getTratta().getCapolinea() + " in " + result + " minuti");
	}
	
}