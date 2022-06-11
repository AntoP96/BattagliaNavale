package database;

import java.util.ArrayList;

public class DBPartita {
	private final int numTentativiMax;
	private ArrayList<String> listaGiocatori;
	private String nomeVincitore;

	public DBPartita(int numTentativiMax, ArrayList<String> listaGiocatori, String nomeVincitore) {
		this.numTentativiMax = numTentativiMax;
		this.listaGiocatori = new ArrayList<>();
		for (String giocatore : listaGiocatori) {
			this.listaGiocatori.add(giocatore);
		}
		this.nomeVincitore = nomeVincitore;
	}

	public int salvaSuDB() {
		String query = "";
		for (String giocatore : listaGiocatori) {
			query = "INSERT INTO partite (numTentativiMax,nomeGiocatore,nomeVincitore) VALUES ( \'"
					+ this.numTentativiMax + "\','" + giocatore + "\','" + this.nomeVincitore + "')";
			try {
				return DBConnectionManager.updateQuery(query);
			} catch (Exception exc) {
				exc.printStackTrace();

				return 0;
			}
		}
		return 0;
	}
}
