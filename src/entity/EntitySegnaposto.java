/**La classe EntitySegnaposto modella il segnaposto del gioco Battaglia Navale. 
 * Ogni volta che viene creata una nuova partita, viene assegnato un segnaposto a ciascun giocatore.
 * Esso viene spostato durante il turno di ogni giocatore, con l'obiettivo di colpire il bersaglio e quindi indovinare le sue coordinate.
 * 
 */
package entity;

import java.util.Objects;

public class EntitySegnaposto {
	/**idSegnaposto viene dichiarato come attributo intero e static e viene aggiornato ogni qualvolta viene creata una nuova istanza di questa classe.
	 * 
	*/
	private static int idSegnaposto = 0;
	/**numRiga attributo private e int che indica la posizione del segnaposto sulla griglia, in particolare indica la riga su cui è posizionato.
	 * 
	 */
	private int numRiga;
	/**numColonna attributo private e int che indica la posizione del segnaposto sulla griglia,in particolare indica la colonna su cui è posizionato.
	 * 
	 */
	private int numColonna;
	/**giocatore attributo private di tipo EntityGiocatore che rappresenta il giocatore che possiede questa istanza di segnaposto per partecipare al gioco.
	 * Settato al momento della creazione di un oggetto di tipo EntitySegnaposto.
	 */
	private EntityGiocatore giocatore;
	
	/**
	 * Costruttore della classe EntitySegnaposto, richiamato dal costruttore della classe EntityPartita quando viene creata una nuova partita dalla classe GestorePartite.
	 * 
	 * @param giocatore: giocatore a cui verrà assegnata questa istanza di segnaposto.
	 */
	public EntitySegnaposto(EntityGiocatore giocatore) {
		this.giocatore = giocatore;
		numRiga = 0;
		numColonna = 0;
		idSegnaposto++;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(giocatore);
	}
	/**
	 * metodo equals in base all'attributo giocatore di EntitySegnaposto
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntitySegnaposto other = (EntitySegnaposto) obj;
		return Objects.equals(giocatore, other.giocatore);
	}
	/**
	 * Questo metodo viene richiamato da un oggetto della classe EntityGiocatore in modo da permettere al Giocatore di spostare il suo Segnaposto all'interno della griglia.
	 * @param numRiga: numero di riga su cui spostare il segnaposto.
	 * @param numColonna: numero di colonna su cui spostare il segnaposto.
	 */
	public void spostaSegnaposto(int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;

	}
	/**
	 * metodo get per ottenere il numero di riga corrente del Segnaposto.
	 * @return: intero che rappresenta il numero di riga.
	 */
	public int getNumRiga() {
		return numRiga;
	}
	/**metodo get per ottenere il numero di riga corrente del Segnaposto.
	 * @return: intero che rappresenta il numero di riga.
	  */
	public int getNumColonna() {
		return numColonna;
	}

}
