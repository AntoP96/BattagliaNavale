
package entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
/**La classe EntityTurno modella il turno di una partita di Battaglia Navale, che viene inizializzata dall'Amministratore, e a cui possono partecipare
 * da uno a tre giocatori. La classe EntityTurno si preoccupa di gestire la logica del turno di una partita:  quindi di verificare il numero di 
 * tentativi del Giocatore che deve effettuare la mossa, inoltre verifica se le coordinate inserite dal Giocatore siano valide e infine decreta 
 * il risultato del Turno del Giocatore restituendo il nome del vincitore o "Turno finito" se il segnaposto non ha raggiunto il bersaglio.
 * 
 */
public class EntityTurno {
	/**idTurno: attributo private di tipo intero che indica l'idTurno per distinguere diversi turni nel corso di una stessa partita
	 * Viene passato dall'oggetto gestore della classe GestorePartite ogni qualvolta la partita è ancora in corso.
	 * 
	 */
	private int idTurno;
	/**partita: attributo private di tipo EntityPartita che specifica la partita a cui è associata questa istanza di EntityTurno.
	 * 
	 */
	private EntityPartita partita;
	/**attributo private di tipo ArrayList<EntityMossa> che contiene le mosse eseguite dai giocatori in gara nel corso di un turno.
	 * 
	 */
	private ArrayList<EntityMossa> listaMosse;
	/**Costruttore della classe EntityTurno che viene richiamato dalla classe EntityPartita quando la partita è appena iniziata o quando un turno è
	 * finito senza che nessuno dei giocatori abbia colpito il bersaglio.
	 * Inizializza la listaMosse al momento della creazione di un nuovo oggetto di tipo EntityPartita.
	 * 
	 * @param idTurno: id del Turno corrente 
	 * @param partita: partita a cui è associato questo Turno.
	 */
	public EntityTurno(int idTurno, EntityPartita partita) {
		this.idTurno = idTurno;
		this.partita = partita;
		listaMosse = new ArrayList<>();
	}
	/**metodo che aggiunge la mossa effettuata dal Giocatore nell'attributo listaMosse di questa classe.
	 * Viene chiamato da un'istanza di EntityTurno nel corso del metodo gestisciTurno,in particolare quando le verifiche preliminari vanno a buon fine.
	 * 
	 * @param mossa: oggetto di tipo EntityMossa, incapsula tutte le informazioni relative alla mossa effettuata.
	 */
	public void addMossa(EntityMossa mossa) {
		listaMosse.add(mossa);
	}
	/** metodo equals sugli attributi idTurno e partita.
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idTurno, partita);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityTurno other = (EntityTurno) obj;
		return idTurno == other.idTurno && Objects.equals(partita, other.partita);
	}
	/**
	 * Metodo che incrementa di un'unità i tentativi del giocatore che ha effettuato una mossa valida.
	 * Viene richiamato dal metodo gestisciTurno di questa classe quando la mossa è valida ma il bersaglio non è stato colpito dal Segnaposto del Giocatore.
	 * @param giocatore: giocatore che ha effettuato la mossa.
	 */
	public void incrementaTentativiGiocatore(EntityGiocatore giocatore) {
		partita.incrementaTentativiGiocatore(giocatore);
	}
	/**Metodo che si occupa della gestione del turno, in particolare gestisce la singola mossa effettuata dal Giocatore:
	 * Verifica che il numero di tentativi del Giocatore sia inferiore al massimo consentito(se falso richiama visualizzaMessaggioErrore di EntityPartita)
	 * Verifica che la mossa effettuata dal giocatore rientri nella griglia(se falso restituisce visualizzaMessaggioErrore di EntityGriglia)
	 * e permette al Giocatore di rieffettuare la mossa.
	 * Una volta superate queste due verifiche, viene creata un'istanza di EntityMossa e salvata nell'attributo listaMosse attraverso il metodo addMossa()
	 * Successivamente si richiama il metodo di Giocatore effettuaMossa e viene spostato il suo Segnaposto attraverso spostaSegnaposto di EntitySegnaposto.
	 * Infine si controlla l'esito della mossa e si stabilisce se la partita è terminata con un vincitore,
	 * altrimenti si incrementa il numero di tentativi del giocatore passato come parametro.
	 * @param giocatore: giocatore che deve effettuare la mossa
	 * @param numRiga: numero di riga dove della mossa
	 * @param numColonna: numero di colonna della mossa
	 * @return : String interpretata poi ai livelli superiori per mostrare al Giocatore l'esito della mossa.
	 */
	public String gestisciTurno(EntityGiocatore giocatore, int numRiga, int numColonna) {
		int idMossa=0;
		if (!partita.verificaNumTentativi(giocatore))
			return partita.visualizzaMessaggioErrore();
		else {
			if (!(partita.getGriglia().verificaCoordinate(numRiga, numColonna)))
				return partita.getGriglia().visualizzaMessaggioErrore();
			else {
				EntityMossa mossa = new EntityMossa(numRiga, numColonna, giocatore, partita,idMossa);
				idMossa++;
				addMossa(mossa);
				giocatore.addMossaInLista(mossa);
				
				giocatore.effettuaMossa(numRiga, numColonna);
				if (partita.getBersaglio().verificaSeColpito(numRiga, numColonna)) {
					
					partita.salvaPartitaSuDB(giocatore.getNome());
					partita.getBersaglio().salvaSuDB(partita.getIdPartita());
					partita.getGriglia().salvaSuDB(partita.getIdPartita());
					return giocatore.getNome();
				} else
					incrementaTentativiGiocatore(giocatore);

				return "Turno finito";
			}
		}
	}
}