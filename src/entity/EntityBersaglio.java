package entity;

import database.DBBersaglio;

public class EntityBersaglio {
	private static int idBersaglio = 0;
	private int numRiga;
	private int numColonna;
	private EntityGriglia griglia;
	private EntityPartita partita;

	public EntityBersaglio(int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
		idBersaglio++;
	}

	public void salvaBersaglioSuDB() {
		DBBersaglio bersaglioDB = new DBBersaglio(numRiga, numColonna);
		bersaglioDB.salvaSuDB();
	}

	public int getNumRiga() {
		return numRiga;
	}

	public int getNumColonna() {
		return numColonna;
	}

	public boolean verificaSeColpito(int numRiga, int numColonna) {
		if ((numRiga == this.numRiga) && (numColonna == this.numColonna))
			return true;
		return false;
	}

}
