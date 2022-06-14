package test;

import org.junit.Test;
import static org.junit.Assert.*;
import control.GestorePartite;
import entity.EntityGiocatore;

import java.util.ArrayList;
/**Classe di test per la funzionalità effettuaMossa dell'applicazione.
 * 
 * @author bad-b
 *
 */
public class TestGestorePartite {
	private GestorePartite gestore;
/**Inizializzo una nuova partita
 * 
 */
	public TestGestorePartite() {
		gestore=new GestorePartite();
		ArrayList<String> listaGiocatori=new ArrayList<>();
		listaGiocatori.add("Antonio");
		listaGiocatori.add("Alfonso");
		listaGiocatori.add("Fabio");
		gestore.inizializzaPartita(listaGiocatori, 3, 4, 4, 3, 3, 5);
	}
/**Metodo di test per effettuaMossa
 * 
 */
	@Test
public void TestEffettuaMossa() {
	
	assertTrue("Turno finito".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 2, 2)));
	assertTrue("Antonio".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 3, 3)));
	assertTrue("Mossa non valida".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 4, 3)));
	gestore.getPartitaCorrente().setTentativiGiocatore(new EntityGiocatore("Antonio"), 3);
	assertFalse("Tentativi esauriti".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 2, 2)));
	assertFalse("Antonio".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 3, 3)));
	assertFalse("Mossa non valida".equals(gestore.effettuaMossa(gestore.getPartitaCorrente().getGiocatoreFromLista("Antonio").getNome(), 0, 4, 3)));
}
}
