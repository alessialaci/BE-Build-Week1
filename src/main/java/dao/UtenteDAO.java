package dao;

import entities.Abbonamento;
import entities.Utente;
import utils.JpaUtils;


public class UtenteDAO extends JpaUtils {

	// METODO PER SALVARE DATI UTENTE NEL DATABASE
	public void save(Utente u) {
		try {
			t.begin();
			em.persist(u);
			t.commit();
			
			System.out.println( "Utente inserito correttamente" );
		} catch(Exception e) {
			logger.error( "Errore durante l'inserimento dell'Utente!" );
		}
	}
	
	
	// METODO PER MODIFICARE LA VALIDITÀ DI UN ABBONAMENTO A true
	public static void updateUtenteById(long id, Abbonamento abbonamento) {
		Utente u = em.find(Utente.class, id);
		
		if(u == null) {
			logger.error("Errore, questo utente non esiste!");
			return;
		}
		
		try {
			u.setAbbonamentoattivo(true);
			u.setAbbonamento(abbonamento);
			
			t.begin();
			em.persist(u);
			t.commit();
			
			
		} catch(Exception e1) {
			logger.error("Errore, abbonamento già attivo!");
		}
	}
	
	
	// METODO PER RECUPERARE UN UTENTE
	public Utente getUtenteById(long id) {
		Utente e = em.find(Utente.class, id);
		
		if( e == null ) {
			logger.error( "Il numero di tessera " + id + " non esiste!" );
		}
		
		return e;
	}

}