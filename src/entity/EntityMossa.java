

package entity;

import java.util.Objects;
/**La classe EntityMossa serve a modellare la mossa effettuata da un Giocatore nel corso di una partita di Battaglia Navale.
 * Ogni istanza di questa classe viene memorizzata nel corso di una partita e fino alla mossa finale che genera la fine della partita.
 * In ogni istante il Giocatore può vedere le mosse che ha effettuato in merito alla partita corrente.
 */
public class EntityMossa {
	/**idMossa attributo intero che viene incrementato ogni volta che un Giocatore effettua una mossa valida.
	 * 
	 */
	private int idMossa = 0;
	/**
	 * numRiga: attributo private che indica il numero di riga della mossa in questione.
	 */
	private final int numRiga;
	/**numColonna: attributo private che indica il numero di colonna della mossa in questione.
	 * 
	 */
	private final int numColonna;
	/**giocatore: attributo private di tipo EntityGiocatore che indica il giocatore che ha effettuato questa istanza di mossa.
	 * 
	 */
	private EntityGiocatore giocatore;
	/**turno: attributo private di tipo EntityTurno che indica il turno della partita in cui è stata effettuata questa mossa.
	 * 
	 */
	private EntityTurno turno;
	/**attributo private di tipo EntityPartita che indica la partita in cui è stata effettuata questa mossa.
	 * 
	 */
	private EntityPartita partita;
	/**
	 * Costruttore della classe EntityMossa richiamato da un oggetto della classe EntityTurno quando le verifiche preliminari sono andate a buon fine
	 * idMossa viene incrementato di un'unità ogni volta che viene creata una nuova istanza di questa classe.
	 * @param numRiga: numero di riga riferito a questa mossa.
	 * @param numColonna: numero di colonna riferito a questa mossa.
	 * @param giocatore: giocatore che ha effettuato la mossa
	 * @param partita: partita in cui è stata effettuata la mossa.
	 * @param idMossa: id della mossa da creare, rispetto al turno.
	 */
	public EntityMossa(int numRiga, int numColonna, EntityGiocatore giocatore, EntityPartita partita,int idMossa) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
		this.giocatore = giocatore;
		this.partita = partita;
		this.idMossa=idMossa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(giocatore, idMossa, partita, turno);
	}
	/**metodo equals sugli attributi giocatore,idMossa,partita e turno.
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
		EntityMossa other = (EntityMossa) obj;
		return Objects.equals(giocatore, other.giocatore) && idMossa == other.idMossa
				&& Objects.equals(partita, other.partita) && Objects.equals(turno, other.turno);
	}
	
}
