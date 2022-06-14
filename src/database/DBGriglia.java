package database;

public class DBGriglia {
	/**numRiga: dimensione di riga della griglia
	 * 
	 */
	private final int numRiga;
	/**numColonna: dimensione di colonna della griglia
	 * 
	 */
	private final int numColonna;
	/**idPartita: id della partita a cui appartiene questa griglia.
	 * 
	 */
	private final int idPartita;
	/**Costruttore della classe DBGriglia che inizializza un nuovo oggetto settando opportunamente i parametri.
	 * 
	 * @param idPartita: id della partita a cui appartiene questa griglia.
	 * @param numRiga: dimensione di riga della griglia
	 * @param numColonna: dimensione di colonna della griglia
	 */
	public DBGriglia(int idPartita,int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
		this.idPartita=idPartita;
	}
	/**Metodo che effettua il salvataggio della griglia sul database richiamato al termine di una partita.
	 * 
	 * @return: l'esito della query.
	 */
	public int salvaSuDB() {
		String query = "INSERT INTO griglia (idPartita,numRiga,numColonna) VALUES (\'"+this.idPartita+"\',\'" + this.numRiga + "\','"
				+ this.numColonna + "')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}

}
