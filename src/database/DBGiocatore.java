package database;

public class DBGiocatore {
	private final String nome;
	private int idPartita;

	public DBGiocatore(String nome) {
		super();
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

	public String getNome() {
		return nome;
	}

	public int getIdPartita() {
		return idPartita;
	}

}
