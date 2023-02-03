package it.epicodegruppo1.dao;

import java.time.LocalDate;

import javax.persistence.Query;

import it.epicodegruppo1.entities.Abbonamento;
import it.epicodegruppo1.entities.Biglietto;
import it.epicodegruppo1.entities.Tessera;
import it.epicodegruppo1.entities.abstracts.Mezzo;
import it.epicodegruppo1.entities.abstracts.TitoloDiViaggio;
import it.epicodegruppo1.utils.JpaUtils;


public class TitoloDiViaggioDAO extends JpaUtils {

	// METODO PER SALVARE I TITOLI DI VIAGGIO SUL DATABASE (ABBONAMENTI O BIGLIETTI)
	public void save(TitoloDiViaggio tit) {
		try {
			t.begin();
			em.persist(tit);
			t.commit();
			
			System.out.println("Ticket inserito correttamente");
		} catch(Exception e) {
			logger.error("Errore durante l'inserimento del ticket " + e);
		}
	}
	

	// METODO PER MODIFICARE LA VALIDITÀ DI UN ABBONAMENTO A false QUANDO È SCADUTO (QUERY NELLA CLASSE ABBONAMENTO)
	public static void updateAbbonamento() {
		t.begin();
		
		Query query = em.createNamedQuery("check_validita_abbonamento");
		query.executeUpdate();
		
		t.commit();
	}


	// METODO PER RECUPERARE LA TESSERA DALL'ID
	public static void checkTessera(long id) {
		Tessera t = em.find(Tessera.class, id);
		
		if( t == null ) {
			System.out.println( "La tessera numero " + id + " non è stata trovata" );
			System.exit(0);
		}
		
		boolean validita = t.isValidita();
		
		if(validita == false) {
			logger.error("Errore: la tessera è scaduta, impossibile creare l'abbonamento");
			System.exit(0);
		} 
	}
	

	// METODO CHE RITORNA IL NUMERO TOTALE DI BIGLIETTI O ABBONAMENTI EMESSI IN UN PERIODO INDICATO
	public static void conteggioTitoli(Class<?> classe, LocalDate startDate, LocalDate endDate) {
	    Query q = em.createQuery("SELECT COUNT(*) FROM " + classe.getName() + " WHERE dataEmissione BETWEEN :start_date AND :end_date");
	    q.setParameter("start_date", startDate);
	    q.setParameter("end_date", endDate);

	    String results = q.getSingleResult().toString();

	    System.out.println("Il numero di " + classe.getSimpleName() + " emessi tra il " + startDate + " e il " + endDate + " sono: " + results);
	}
	

	// METODO PER CONTROLLARE LA VIDIMAZIONE DI UN BIGLIETTO O LA SCADENZA DI UN ABBONAMENTO E PERMETTE ALL'UTENTE DI SALIRE O NO SU UN MEZZO
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
				
				System.out.println("Biglietto vidimato correttamente. Puoi partire");
				MezzoDAO.conteggioCorseMezzo(idMezzo);
			} else {
				System.out.println("Biglietto già utilizzato. Non puoi partire");
			}
		} else if(tipo == Abbonamento.class) {
			Abbonamento a = em.find(Abbonamento.class, idTicket);
			LocalDate validita = a.getDataScadenza();
			
			if(validita.isAfter(LocalDate.now())) {
				System.out.println("Abbonamento valido. Puoi partire");
				MezzoDAO.conteggioCorseMezzo(idMezzo);
			} else {
				System.out.println("Abbonamento scaduto. Non puoi partire");
				System.exit(0);
			}
		}
	}
	
}