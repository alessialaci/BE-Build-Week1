package it.epicodegruppo1.dao;

import java.time.LocalDate;

import javax.persistence.Query;

import it.epicodegruppo1.entities.abstracts.Mezzo;
import it.epicodegruppo1.utils.JpaUtils;


public class MezzoDAO extends JpaUtils {

	// METODO PER SALVARE IL MEZZO SUL DATABASE
	public void save(Mezzo m) {
		try {
			t.begin();
			em.persist(m);
			t.commit();
			
			System.out.println("Mezzo inserito correttamente");
		} catch(Exception e) {
			logger.error("Errore durante l'inserimento del mezzo");
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
	
	
	// METODO PER INCREMENTARE IL NUMERO DELLE CORSE
	public static void conteggioCorseMezzo( int id) {
		Mezzo m = em.find(Mezzo.class, id);
		
		int corse = m.getNCorse() + 1;
		m.setNCorse(corse);
		
		t.begin();
		em.persist(m);
		t.commit();
	}
	
	
	// METODO CHE SETTA LE DATE DI MANUTENZIONE DI UN MEZZO CHE SUPERA LE 10 CORSE
	public static void queryManutenzione() {
		t.begin();

		Query q = em.createQuery("UPDATE Mezzo m SET m.inServizio = false, m.inizioManutenzione = :startDate, m.fineManutenzione = :endDate, m.nCorse = 0 WHERE m.nCorse = 10");
		q.setParameter("startDate", LocalDate.now());
		q.setParameter("endDate", LocalDate.now().plusDays(1));
		q.executeUpdate();
		
		Query q2 = em.createQuery("UPDATE Mezzo m SET m.inServizio = true, m.inizioManutenzione = NULL, m.fineManutenzione = NULL WHERE m.fineManutenzione <= CURRENT_DATE");
		q2.executeUpdate();
		
		t.commit();
	}

}