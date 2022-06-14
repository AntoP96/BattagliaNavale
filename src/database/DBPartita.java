/**Classe che si occupa del salvataggio di una partita sul database con il numero di tentativi massimo, la lista di giocatori e il nome del vincitore
 * se presente.
 * 
 */
package database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DBPartita {
	/**idPartita: id della partita corrente.
	 * 
	 */
	private int idPartita;
	/**numTentativiMax: attributo di tipo intero che indica il numero di tentativi massimo relativo a questa istanza di partita
	 * 
	 */
	private int numTentativiMax;
	/**listaGiocatori : attributo che indica la lista dei giocatori che hanno partecipato alla partita.
	 * 
	 */
	private String giocatore;
	/**nomeVincitore: attributo che indica il nome del vincitore, se presente, della partita.
	 * 
	 */
	private String nomeVincitore;
	/**Costruttore vuoto utilizzato per creare un oggetto e prendere l'id della partita da creare attraverso il metodo selectIdPartitaDaDB()
	 * 
	 */
	public DBPartita() {
		
	}
	/**Costruttore della classe DBPartita che viene richiamato al termine di una partita per salvarla sul database attraverso il metodo salvaSuDB()
	 * @param idPartita: id della partita da salvare
	 * @param numTentativiMax : intero che indica il numero di tentativi massimo di questa partita
	 * @param listaGiocatori : collezione dei giocatori che hanno partecipato alla partita
	 * @param nomeVincitore: nome del vincitore della partita, se presente.
	 */
	public DBPartita(int idPartita,int numTentativiMax, String giocatore, String nomeVincitore) {
		this.idPartita=idPartita;
		this.numTentativiMax = numTentativiMax;
		this.giocatore = giocatore;
		
		this.nomeVincitore = nomeVincitore;
	}
	/**Metodo che si occupa di salvare la partita sul database con i dati relativi ad essa. Viene richiamato alla fine di ogni partita dalla classe EntityTurno.
	 * 
	 * @return : l'esito della query.
	 */
	public int salvaSuDB() {
		String query = "";
		
			query = "INSERT INTO partite (idpartita,numtentativimax,nomegiocatore,nomevincitore) VALUES (\'"+ this.idPartita +"\', \'"
					+ this.numTentativiMax + "\','" + this.giocatore + "\','" + this.nomeVincitore + "')";
			try {
				return DBConnectionManager.updateQuery(query);
			} catch (Exception exc) {
				exc.printStackTrace();

				return 0;
			}
		
	}
	/**Metodo utilizzato per contare il numero di partite già create e restituisce il prossimo id da assegnare a partita.
	 * 
	 * @return: i'id della nuova partita da creare.
	 */
	public int selectIdPartitaDaDB() {
		int i=0;
		try {
		ResultSet rs=DBConnectionManager.selectQuery("SELECT DISTINCT idPartita  FROM partite ORDER BY idPartita");
		while(rs.next()) {
			i++;
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return i;
	}
}
