/**Questa classe si occupa del salvataggio di un giocatore sul database memorizzando il suo nome.
 * 
 * 
 */
package database;

import java.sql.ResultSet;

public class DBGiocatore {
	/**nome: attributo final di tipo String, indica il nome del giocatore.
	 * 
	 */
	private final String nome;
	/**Costruttore della classe, setta l'attributo attraverso la stringa passata come parametro.
	 * 
	 * @param nome: nome del giocatore
	 */
	public DBGiocatore(String nome) {
		this.nome = nome;

	}
	/**Metodo che salva un'istanza di DBGiocatore sul database attraverso una query di insert.
	 * 
	 * @return: risultato della query
	 */
	public int salvaSuDB() {
		String query = "INSERT INTO utenti (nome) VALUES ( \'" + this.nome + "\')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}
	/**Metodo che seleziona il giocatore da inserire dal database per verificare se già esiste ed evitare errori di insert con PrimaryKey uguale.
	 * 
	 * @return: 1 se presente,0 altrimenti.
	 * 				
	 */
	public int selectDaDB() {
		try {
			ResultSet rs=DBConnectionManager.selectQuery("SELECT * FROM utenti WHERE nome='"+this.nome+"';");
			while(rs.next()) {
				return 1;
			}
			return 0;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
