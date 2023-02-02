package dao;

import entities.Abbonamento;
import entities.Utente;
import utils.JpaUtils;

public class UtenteDAO extends JpaUtils {

	public void save(Utente u) {
		
		try {
			
			t.begin();
			em.persist(u);
			t.commit();
			
			System.out.println( "Utente inserito correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento dell'Utente!!" );
		}
		
	}
	
	public Utente getUtenteById(long id) {
		Utente e = em.find(Utente.class, id);
		
		if( e == null ) {
			System.out.println( "Il numero di tessera " + id + " non esiste!" );
		}
		
		return e;
	}
	
	public static void updateUtenteById(long id, Abbonamento abbonamento) {
		Utente u = em.find(Utente.class, id);
		
		if(u == null) {
			System.out.println("Errore, questo utente non esiste!");
			return;
		}
		
		try {
			u.setAbbonamentoattivo(true);
			u.setAbbonamento(abbonamento);
			
			t.begin();
			em.persist(u);
			t.commit();
			
			
		} catch(Exception e1) {
			System.out.println("Errore, abbonamento già attivo!");
		}
		
	}
	
}