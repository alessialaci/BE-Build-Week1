package dao;

import entities.Tratta;
import utils.JpaUtils;

public class TrattaDAO extends JpaUtils {

	// METODO PER SALVARE LA TRATTA SUL DATABASE
	public void save(Tratta tr) {
		try {
			t.begin();
			em.persist(tr);
			t.commit();
			
			System.out.println("Tratta inserita correttamente");
		} catch(Exception e) {
			logger.error("Errore durante l'inserimento della tratta!" + e);
		}
	}
	
}