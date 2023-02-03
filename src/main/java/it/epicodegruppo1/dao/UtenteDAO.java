package it.epicodegruppo1.dao;

import it.epicodegruppo1.entities.Abbonamento;
import it.epicodegruppo1.entities.Utente;
import it.epicodegruppo1.utils.JpaUtils;


public class UtenteDAO extends JpaUtils {

	// METODO PER SALVARE DATI UTENTE NEL DATABASE
	public void save(Utente u) {
		try {
			t.begin();
			em.persist(u);
			t.commit();
			
			System.out.println("Utente inserito correttamente");
		} catch(Exception e) {
			logger.error("Errore durante l'inserimento dell'Utente " + e);
		}
	}
	
	
	// METODO PER MODIFICARE LA VALIDITÀ DI UN ABBONAMENTO A true
	public static void updateUtenteById(long id, Abbonamento abbonamento) {
		Utente u = em.find(Utente.class, id);
		
		if(u == null) {
			System.out.println("Errore, questo utente non esiste");
			return;
		}
		
		try {
			u.setAbbonamento(abbonamento);
			
			t.begin();
			em.persist(u);
			t.commit();
			
		} catch(Exception e1) {
			logger.error("Errore, abbonamento già attivo");
		}
	}
	
	
	// METODO PER RECUPERARE UN UTENTE
	public Utente getUtenteById(long id) {
		Utente e = em.find(Utente.class, id);
		
		if(e == null) {
			System.out.println("Il numero di tessera " + id + " non esiste");
		}
		
		return e;
	}

}