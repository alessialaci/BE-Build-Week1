package it.epicodegruppo1.app;

import java.time.LocalDate;

import it.epicodegruppo1.dao.MezzoDAO;
import it.epicodegruppo1.dao.TesseraDAO;
import it.epicodegruppo1.dao.TicketingDAO;
import it.epicodegruppo1.dao.TitoloDiViaggioDAO;
import it.epicodegruppo1.entities.Abbonamento;
import it.epicodegruppo1.entities.Biglietto;
import it.epicodegruppo1.utils.Gestionale;
import it.epicodegruppo1.utils.Menu;


public class App {

	public static void main(String[] args) {
		//Gestionale.addMezzi();
		//Menu.saveDistributore();
		//Menu.saveRivenditore();
		
		Gestionale.counter();
		Menu.runApp();

		//TitoloDiViaggioDAO.conteggioTitoli(Abbonamento.class, LocalDate.now(), LocalDate.now().plusDays(30));
		//TicketingDAO.getTitoliEmessi(1);
		//MezzoDAO.conteggioBigliettiVidimati(LocalDate.now(), LocalDate.now().plusDays(30));
	}
	
}