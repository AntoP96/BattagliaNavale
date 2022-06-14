
package entity;

import java.util.ArrayList;
import java.util.Objects;

import database.DBGiocatore;
/**La classe EntityGiocatore modella il giocatore che partecipa ad una partita di Battaglia Navale. 
 * Nell'ambito di una partita, questa classe ottiene un'istanza di Segnaposto che usa per cercare di colpire il bersaglio e vincere la partita.
 * Inoltre memorizza la lista di mosse che ha effettuato il giocatore nella partita corrente che può essere mostrata a video al giocatore "reale".
 * 
 */
public class EntityGiocatore {
	/**nome : attributo private e final di tipo String che identifica i diversi Giocatori creati nell'applicazione Battaglia Navale.
	 * 
	 */
	private final String nome;
	/**segnaposto: attributo di tipo EntitySegnaposto, viene inizializzato al momento della creazione di una nuova partita.
	 * 
	 */
	
	private EntitySegnaposto segnaposto;
	/**listaMosse: attributo di tipo ArrayList<EntityMossa> che indica la lista di mosse effettuata da un giocatore all'intero di una partita.
	 * 
	 */
	private ArrayList<EntityMossa> listaMosse;
	/**
	 * Costruttore della classe EntityGiocatore richiamato da un oggetto della classe GestorePartite quando l'Amministratore inserisce i dati in input.
	 * Esso crea una nuova istanza di questa classe e inizializza l'attributo listaMosse.
	 * @param nome
	 */
	public EntityGiocatore(String nome) {
		this.nome = nome;
		
		listaMosse = new ArrayList<>();
	}
	/**Metodo get per ottenere l'attributo listaMosse di EntityGiocatore. Questo metodo viene richiamato quando il giocatore chiede di visualizzare
	 * l'elenco delle posizioni del suo segnaposto durante la partita corrente.(NON é STATO IMPLEMENTATO)
	 * 
	 * @return: la lista di mosse effettuate dal Giocatore.
	 */
	public ArrayList<EntityMossa> getListaMosse() {
		return listaMosse;
	}
	/**Metodo che permette il salvataggio di un Giocatore sul Database richiamando il metodo salvasuDB() sull'oggetto della classe DBGIocatore appena creato.
	 * Verifica anche se il giocatore da inserire è gia presente prima dell'inserimento.
	 */
	public void salvaGiocatoreSuDB() {
		DBGiocatore giocatoreDB = new DBGiocatore(nome);
		if(giocatoreDB.selectDaDB()==0)
			giocatoreDB.salvaSuDB();

	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	/**
	 * metodo equals sull'attributo nome di Giocatore.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityGiocatore other = (EntityGiocatore) obj;
		return Objects.equals(nome, other.nome);
	}


	/**Metodo che aggiunge un'istanza della classe EntityMossa nell'attributo listaMosse di questa classe.Serve ad aggiornare la lista di mosse effettuate.
	 * Viene richiamato dalla classe EntityTurno quando il Giocatore effettua una mossa valida.
	 * 
	 * @param mossa: la mossa appena effettuata
	 */
	public void addMossaInLista(EntityMossa mossa) {
		listaMosse.add(mossa);
	}
	/**Metodo che permette al Giocatore di "effettuare una mossa" 
	 * cioè sposta il segnaposto  appartenente all'istanza di Giocatore su cui viene chiamato questo metodo.
	 * 
	 * @param numRiga: numero di riga dove spostare il Segnaposto
	 * @param numColonna: numero di colonna dove spostare il Segnaposto
	 */
	public void effettuaMossa(int numRiga, int numColonna) {
		segnaposto.spostaSegnaposto(numRiga, numColonna);

	}
	/**Metodo richiamato dalla classe EntityPartita quando viene inizializzata una nuova partita. Esso serve a settare un'istanza di segnaposto 
	 * a questa istanza di Giocatore in modo da assegnare un Segnaposto ad ogni giocatore in gara.
	 * 
	 * @param segnaposto:il segnaposto da assegnare al giocatore
	 */
	public void setSegnaposto(EntitySegnaposto segnaposto) {
		this.segnaposto = segnaposto;
	}
	/**Metodo get per l'attributo nome di Giocatore
	 *
	 * @return: il valore dell'attributo nome dell'istanza di giocatore su cui viene chiamato il metodo.
	 */
	public String getNome() {
		return nome;
	}

}
