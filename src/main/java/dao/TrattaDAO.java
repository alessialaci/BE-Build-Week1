package dao;

import entities.Tratta;
import utils.JpaUtils;

public class TrattaDAO extends JpaUtils {

	public void save(Tratta tr) {
		
		try {
			
			t.begin();
			em.persist(tr);
			t.commit();
			
			System.out.println( "Tratta inserita correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento della tratta!!" );
		}
		
	}
	
}