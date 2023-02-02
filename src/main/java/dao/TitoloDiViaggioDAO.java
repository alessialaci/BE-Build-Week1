package dao;

import java.time.LocalDate;

import javax.persistence.Query;

import entities.Abbonamento;
import entities.Biglietto;
import entities.Tessera;
import entities.abstracts.Mezzo;
import entities.abstracts.TitoloDiViaggio;
import utils.JpaUtils;

public class TitoloDiViaggioDAO extends JpaUtils {

	public void save(TitoloDiViaggio tit) {
		
		try {
			
			t.begin();
			em.persist(tit);
			t.commit();
			
			System.out.println( "Ticket inserito correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento del ticket!!" + e );
		}
		
	}
	
	public static void checkTessera(long id) {
		Tessera t = em.find(Tessera.class, id);
		
		if( t == null ) {
			System.out.println( "La tessera numero " + id + " non è stata trovata!" );
			System.exit(0);
		}
		
		boolean validita = t.isValidita();
		
		if(validita == false) {
			System.out.println("ERROR! La tessera è scaduta, impossibile creare l'abbonamento!");
			System.exit(0);
		} 
	}
	
	public static void conteggioTitoli(Class<?> classe, LocalDate startDate, LocalDate endDate) {
	    Query q = em.createQuery("SELECT COUNT(*) FROM " + classe.getName() + " WHERE dataEmissione BETWEEN :start_date AND :end_date");
	    q.setParameter("start_date", startDate);
	    q.setParameter("end_date", endDate);

	    String results = q.getSingleResult().toString();

	    System.out.println("Il numero di " + classe.getSimpleName() + " emessi tra il " + startDate + " e il " + endDate + " sono: " + results);
	}
	
	public static void getTitolo(int idTicket, int idMezzo) {
		TitoloDiViaggio tit = em.find(TitoloDiViaggio.class, idTicket);
		
		if(tit == null) {
			System.out.println("Il titolo numero " + idTicket + " non è stato trovato!");
			System.exit(0);
		}
		
		Class<?> tipo = tit.getClass();
		if(tipo == Biglietto.class) {
			Biglietto b = em.find(Biglietto.class, idTicket);
			boolean validita = b.isTimbrato();
			if(validita == false) {
				b.setVidimazione(LocalDate.now());
				b.setTimbrato(true);
				Mezzo m = em.find(Mezzo.class, idMezzo);
				b.setMezzo(m);
				int counter = m.getBigliettiVidimati() + 1;
				m.setBigliettiVidimati(counter);
				
				t.begin();
				em.persist(b);
				em.persist(m);
				t.commit();
				
				System.out.println("Biglietto vidimato correttamente! Puoi partire.");
			} else {
				System.out.println("Biglietto già utilizzato! Acquistane uno nuovo. Non puoi partire!");
			}
			
		} else if(tipo == Abbonamento.class) {
			Abbonamento a = em.find(Abbonamento.class, idTicket);
			LocalDate validita = a.getDataScadenza();
			if(validita.isAfter(LocalDate.now())) {
				System.out.println("Abbonamento valido! Puoi partire.");
			} else {
				System.out.println("Abboanmento scaduto! Non puoi partire.");
				System.exit(0);
			}
		}
	}
	
	public static void updateAbbonamento() {
		t.begin();
		
		Query query = em.createNamedQuery("check_validita_abbonamento");
		int rowAffected = query.executeUpdate();
		
		t.commit();
	}
	
	public static void updateAbbonamentoUtente() {
		t.begin();
		
		Query query = em.createNamedQuery("check_validita_abbonamentoUtente");
		int rowAffected = query.executeUpdate();
		
		t.commit();
	}
	
}