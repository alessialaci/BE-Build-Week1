package dao;

import java.time.LocalDate;

import javax.persistence.Query;

import entities.Tessera;
import utils.JpaUtils;


public class TesseraDAO extends JpaUtils {

	// METODO PER SALVARE LA TESSERA SUL DATABASE
	public void save(Tessera tes) {
		try {
			t.begin();
			em.persist(tes);
			t.commit();
			
			System.out.println( "Tessera inserita correttamente" );
		} catch(Exception e) {
			logger.error( "Errore durante l'inserimento della tessera!" );
		}
	}
	
	
	// METODO PER MODIFICARE VALIDITA DELLA TESSERA SUL DATABASE (QUERY NELLA CLASSE TESSERA)
	public static void update() {
		t.begin();
		
		Query query = em.createNamedQuery("check_validita");
		query.executeUpdate();
		
		t.commit();
	}
	
	
	// METODO PER RECUPERARE DATI DELLA TESSERA, COMPRESA VALIDITA' DELLA TESSERA E DEL RELATIVO ABBONAMENTO
	public static void getDatiTessera(long id) {
		Tessera t = em.find(Tessera.class, id);
		
		if( t == null ) {
			System.out.println( "La tessera numero " + id + " non è stata trovata!" );
			return;
		}
		
		System.out.println( "Dati tessera #" + id );
		System.out.printf(  
			"Nome: %s | Cognome: %s | Email: %s | Data emissione: %s | Data scadenza: %s%n",
			t.getNome(), t.getCognome(), t.getEmail(), t.getDataEmissione().toString(), t.getDataScadenza().toString());
		
		boolean validita = t.isValidita();
		
		if(validita == true) {
			System.out.println("La tessera è valida!");
		} else {
			System.out.println("La tessera è scaduta! Rinnovala!");
		}
		
		boolean validitaAbbonamento = t.isAbbonamentoattivo();
		
		if(validitaAbbonamento == true) {
			System.out.println("Hai un abbonamento attivo che scade il " + t.getAbbonamento().getDataScadenza());
		} else {
			System.out.println("Non hai un abbonamento attivo!");
		}
	}
	
	
	// METODO PER RINNOVARE LA TESSERA CHE, SE È SCADUTA, MODIFICA LA VALIDITA' A true E LA NUOVA DATA DI SCADENZA AD UN ANNO DA OGGI
	public static void rinnovaTessera(long id) {
		Tessera tes = em.find(Tessera.class, id);
		
		if( tes == null ) {
			logger.error( "La tessera numero " + id + " non è stata trovata!" );
			return;
		}
		
		boolean validita = tes.isValidita();
		
		if(validita == true) {
			System.out.println("La tessera è già valida impossibile rinnovarla!");
		} else {
			tes.setValidita(true);
			tes.setDataScadenza(LocalDate.now().plusYears(1));
			
			t.begin();
			em.persist(tes);
			t.commit();
			
			System.out.println("Tessera rinnovata!");
			System.out.println("La nuova data di scadenza è:" + tes.getDataScadenza());
		}
	}
	
}