package utils;

import dao.MezzoDAO;
import dao.TrattaDAO;
import entities.Autobus;
import entities.Tram;
import entities.Tratta;

public class Gestionale {

	public static void addMezzi() {
		
		Tratta tr1 = new Tratta("Stazione Termini", "Stazione Tiburtina", 50);
		Tratta tr2 = new Tratta("Stazione Termini", "Villa Borghese", 20);
		Tratta tr3 = new Tratta("Stazione Tiburtina", "Piazza del Popolo", 6);
		Tratta tr4 = new Tratta("Stazione Tiburtina", "Castel Sant'Angelo", 15);
		saveTratta(tr1);
		saveTratta(tr2);
		saveTratta(tr3);
		saveTratta(tr4);
		
		Autobus a1 = new Autobus(80, true, tr1, "780");
		Autobus a2 = new Autobus(80, true, tr2, "8");
		Autobus a3 = new Autobus(80, false, tr3, "071");
		Autobus a4 = new Autobus(80, true, tr4, "720");
		saveAutobus(a1);
		saveAutobus(a2);
		saveAutobus(a3);
		saveAutobus(a4);
		
		Tram t1 = new Tram(100, false, tr1, "2");
		Tram t2 = new Tram(100, true, tr2, "5");
		Tram t3 = new Tram(100, true, tr3, "14");
		Tram t4 = new Tram(100, true, tr4, "19");
		saveTram(t1);
		saveTram(t2);
		saveTram(t3);
		saveTram(t4);
		
		
	}
	
	public static Autobus saveAutobus(Autobus a) {
		MezzoDAO aut = new MezzoDAO();
		aut.save(a);
		return a;
	}
	
	public static Tram saveTram(Tram t) {
		MezzoDAO tram = new MezzoDAO();
		tram.save(t);
		return t;
	}
	
	public static Tratta saveTratta(Tratta tr) {
		TrattaDAO tratta = new TrattaDAO();
		tratta.save(tr);
		return tr;
	}
	
	public static void counter() {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_BACKGROUND = "\u001B[41m";
		long endTime = System.currentTimeMillis() + (3 * 1000);
	    int count = 0;
	    System.out.println("Caricamento dati" );
	    while (System.currentTimeMillis() < endTime) {
	      try {
	        Thread.sleep(250);
	        count++;
	        System.out.print(ANSI_RED + ANSI_BACKGROUND + "." + ANSI_RESET + " ");
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    System.out.println();
	    System.out.println("Dati caricati con successo!");
	    System.out.println(ANSI_RED + "-------------------------------------------" + ANSI_RESET);
	}
	
}