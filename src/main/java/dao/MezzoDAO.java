package dao;

import java.time.LocalDate;

import javax.persistence.Query;

import entities.abstracts.Mezzo;
import utils.JpaUtils;


public class MezzoDAO extends JpaUtils {

	// METODO PER SALVARE IL MEZZO SUL DATABASE
	public void save(Mezzo m) {
		try {
			t.begin();
			em.persist(m);
			t.commit();
			
			System.out.println( "Mezzo inserito correttamente" );
		} catch(Exception e) {
			logger.error( "Errore durante l'inserimento del mezzo" );
		}
	}
	
	
	// METODO PER CONTARE IL NUMERO TOTALE DEI BIGLIETTI VIDIMATI IN UN PERIODO INDICATO
	public static void conteggioBigliettiVidimati(LocalDate startDate, LocalDate endDate) {
		try {
			Query q = em.createQuery("SELECT SUM(m.bigliettiVidimati) FROM Mezzo m WHERE EXISTS (SELECT b FROM Biglietto b WHERE b.vidimazione BETWEEN :startDate AND :endDate AND m.mezzo_id = b.mezzo)");
			q.setParameter("startDate", startDate);
			q.setParameter("endDate", endDate);

			String results = q.getSingleResult().toString();

			System.out.println("Il numero di biglietti vidimati dal " + startDate + " al " + endDate + " è: " + results);
			
		} catch(Exception e) {
			logger.error("Nessun biglietto vidimato dal " + startDate + " al " + endDate);
		}
	}

}