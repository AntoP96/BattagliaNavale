package database;

public class DBBersaglio {
	/**numRiga: numero di riga dove è posizionato il bersaglio
	 * 
	 */
	private final int numRiga;
	/**numColonna: numero di colonna dove è posizionato il bersaglio
	 * 
	 */
	private final int numColonna;
	/**idPartita: id della partita a cui è associato questo bersaglio.
	 * 
	 */
	private final int idPartita;
	/**Costruttore della classe DBBersaglio, restituisce una nuova istanza della classe settando opportunamente i parametri.
	 * 
	 * @param idPartita: id della partita a cui è associato questo bersaglio.
	 * @param numRiga: numero di riga dove è posizionato il bersaglio
	 * @param numColonna: numero di colonna dove è posizionato il bersaglio
	 */
	public DBBersaglio(int idPartita,int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
		this.idPartita=idPartita;
	}
	/**Metodo che permette il salvataggio del bersaglio sul database al termine di una partita.
	 * 
	 * @return: l'esito della query.
	 */
	public int salvaSuDB() {
		String query = "INSERT INTO bersaglio (idPartita,numRiga,numColonna) VALUES (\'"+this.idPartita+"\',\'" +this.numRiga + "\','"
				+ this.numColonna + "')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}
}
