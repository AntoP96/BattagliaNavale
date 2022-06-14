/**La classe EntityGriglia modella la griglia del gioco BattagliaNavale. 
 * Essa viene creata dalla classe GestorePartite quando l'Amministratore sceglie di creare una nuova partita.
 * Essa rappresenta il campo da gioco su cui i giocatori possono muovere i loro segnaposti per indovinare la posizione del bersaglio, su di essa posizionato.
 * 
 */
package entity;

import java.util.*;
import database.DBGriglia;


public class EntityGriglia {
	/**numRiga è un attributo private intero che rappresenta la dimensione della griglia rispetto alle sue righe.
	 * 
	 */
	private int numRiga;
	/**numColonna è un attributo di tipo intero che rappresenta la dimensione della griglia rispetto alle sue colonne.
	 * 
	 */
	private int numColonna;

	/** bersaglio rappresenta il bersaglio che ad ogni partita viene posizionato sulla griglia e rappresenta lo scopo del gioco.
	 * 
	 */
	private EntityBersaglio bersaglio;
	
	private Integer idPartita;
	/**Costruttore della classe EntityGriglia, richiamato dal GestorePartite quando l'Amministratore ha inserito i dati inerenti alla partita in input.
	 * Vengono settati il numero di righe e colonne della griglia.
	 * 
	 * @param numRighe: numero di righe da cui sarà composta la griglia.
	 * @param numColonne: numero di colonne da cui sarà composta la griglia.
	 */
	public EntityGriglia(int numRighe, int numColonne) {
		this.numRiga = numRighe;
		this.numColonna = numColonne;
		
	}
	/**
	 * Setta l'idPartita nell'oggetto griglia su cui viene chiamato il metodo.
	 * Tale parametro viene utilizzato per distinguere le diverse istanze EntityGriglia create in base all'idPartita.
	 * Il parametro viene passato dalla classe GestorePartite al momento della creazione di una nuova partita.
	 * @param idPartita: id della partita a cui è associata l'istanza corrente di EntityGriglia.
	 */
	public void setIdPartita(Integer idPartita) {
		this.idPartita=idPartita;
	}
	
	
	@Override
	
	public int hashCode() {
		return Objects.hash(idPartita);
	}
	/**
	 * metodo equals basato sull'attributo idPartita di EntityGriglia.
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
		EntityGriglia other = (EntityGriglia) obj;
		return Objects.equals(idPartita, other.idPartita);
	}
	/**Il metodo verificaCoordinate viene richiamato dalla classe EntityTurno
	 * quando la verifica dei tentativi del giocatore che ha effettuato la mossa è andata a buon fine.
	 * Questo metodo controlla se la mossa del giocatore è valida e restituisce un boolean come risultato.
	 * In particolare verifica che la riga e la colonna fornite in input dal Giocatore rientrino nella dimensione della griglia.
	 * In caso contrario restituisce un messaggio di errore che è stato gestito con un altro metodo
	 * 
	 * 
	 * @param numRiga: numRiga fornito in input dal giocatore
	 * @param numColonna: numColonna fornito in input dal giocatore
	 * @return: true se la mossa è valida, false altrimenti.
	 */
	public boolean verificaCoordinate(int numRiga, int numColonna) {
		if (numRiga >= this.numRiga || numColonna >= this.numColonna)
			return false;
		return true;
	}
	/**
	 * Metodo che restituisce un messaggio di errore quando il Giocatore prova a muovere il segnaposto al di fuori della griglia
	 * @return: messaggio di errore interpretato poi dalla classe BattagliaNavale per mostrarlo sulla JFrame al Giocatore.
	 */


	public String visualizzaMessaggioErrore() {
		return "Mossa non valida";
	}
	/**Metodo che permette il salvataggio della griglia sul database.
	 * 
	 * @param idPartita: id della partita a cui è associata questa istanza di griglia.
	 */
	public void salvaSuDB(int idPartita) {
		DBGriglia grigliaDB= new DBGriglia(idPartita,numRiga,numColonna);
		grigliaDB.salvaSuDB();
	}
}
