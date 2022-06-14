/**La classe EntityBersaglio modella il bersaglio del gioco BattagliaNavale, esso viene creato e posizionato sulla griglia all'inizio di ogni partita. 
 * Se viene colpito dal segnaposto di uno dei giocatori in gara, questo evento denota il termine della partita e la vittoria del giocatore che ha indovinato la sua posizione.
 * 
 */
package entity;

import java.util.Objects;
import java.util.Random;
import database.DBBersaglio;
import database.DBGriglia;

public class EntityBersaglio {
	/**idBersaglio è dichiarato come attributo intero e viene inizializzato ad un valore random ogni qualvolta viene chiamato il costruttore di questa classe.
	 * 
	 */
	private  int idBersaglio = 0; 
	/** numRiga è un attributo di tipo intero che denota la posizione del bersaglio sulla griglia,in particolare indica la riga dove è posizionato.
	 * Viene settato al momento della creazione della partita da parte dell'Amministratore.
	 * 
	 */
	private final int numRiga;
	/**numColonna è un attributo di tipo intero che denota la posizione del bersaglio sulla griglia,in particolare indica la colonna dove è posizionato.
	 * Viene settato al momento della creazione della partita da parte dell'Amministratore.
	 * 
	 */
	private final int numColonna;
	
	/**
	 * Costruttore della classe EntityBersaglio, richiamato dalla classe GestorePartite al momento della creazione di una partita.
	 * Aggiorna l'id incrementandolo di un' unità e setta numRiga e numColonna posizionando virtualmente il bersaglio sulla griglia.
	 * @param numRiga: denota la posizione del bersaglio sulla griglia, in particolare indica la riga dove è posizionato.
	 * @param numColonna:  denota la posizione del bersaglio sulla griglia, in particolare indica la colonna dove è posizionato.
	 */
	public EntityBersaglio(int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
		Random random=new Random();
		idBersaglio=random.nextInt();
	}

	/**
	 * Metodo get per l'attributo private numRiga.
	 * @return numRiga: posizione di riga del bersaglio sulla griglia.
	 */

	
	public int getNumRiga() {
		return numRiga;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idBersaglio, numColonna, numRiga);
	}
	/**metodo equals sugli attributi id,numRiga e numColonna.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBersaglio other = (EntityBersaglio) obj;
		return idBersaglio == other.idBersaglio && numColonna == other.numColonna && numRiga == other.numRiga;
	}

	/**
	 * Metodo get per l'attributo private numColonna.
	 * 
	 * @return numColonna: posizione di colonna del bersaglio sulla griglia.
	 */
	public int getNumColonna() {
		return numColonna;
	}
	/**
	 * Metodo che verifica la mossa di un giocatore.
	 * In particolare il bersaglio verifica se il Segnaposto del Giocatore l'ha raggiunto, ovvero gli attributi numRiga e numColonna sono uguali alle coordinate inserite dal giocatore.
	 * @param numRiga : coordinata di riga del Segnaposto del Giocatore. Inserita in input dal Giocatore per muovere il Segnaposto.
	 * @param numColonna :  coordinata di colonna del Segnaposto del Giocatore. Inserita in input dal Giocatore per muovere il Segnaposto.
	 * @return: il metodo viene richiamato dalla classe EntityTurno una volta che le verifiche preliminari sono andate a buon fine.
	 * Solo quando il metodo restituisce true, la classe EntityTurno invia il segnale di termine partita visto che il bersaglio è stato colpito.
	 * Nel caso contrario (return false) la partita resta in corso e la classe EntityTurno si preoccupa di notificare la classe GestorePartite.
	 */
	public boolean verificaSeColpito(int numRiga, int numColonna) {
		if ((numRiga == this.numRiga) && (numColonna == this.numColonna))
			return true;
		return false;
	}
	
	/**Metodo che permette il salvataggio del bersaglio di una partita conclusa sul database.
	 * 
	 * @param idPartita: id della partita a cui apparteneva quest'istanza di bersaglio.
	 */
	public void salvaSuDB(int idPartita) {
		DBBersaglio bersaglioDB= new DBBersaglio(idPartita,numRiga,numColonna);
		bersaglioDB.salvaSuDB();
	}
}
