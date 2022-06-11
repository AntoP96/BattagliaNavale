package database;

public class DBGiocatore {
	private final String nome;
	
	public DBGiocatore(String nome) {
		this.nome = nome;

	}

	public int salvaSuDB() {
		String query = "INSERT INTO utenti (nome) VALUES ( \'" + this.nome + "\')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}
}
