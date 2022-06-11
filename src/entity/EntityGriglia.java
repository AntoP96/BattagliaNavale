package entity;

import java.util.*;

import database.DBGriglia;

public class EntityGriglia {
	private int numRiga;
	private int numColonna;
	private ArrayList<EntitySegnaposto> listaSegnaposti;
	private EntityPartita partita;
	private EntityBersaglio bersaglio;

	public EntityGriglia(int numRighe, int numColonne) {
		this.numRiga = numRighe;
		this.numColonna = numColonne;
		listaSegnaposti = new ArrayList<>();
	}
	
	public void salvaGrigliaSuDB() {
		DBGriglia grigliaDB = new DBGriglia(numRiga, numColonna);
		grigliaDB.salvaSuDB();
	}

	public ArrayList<EntitySegnaposto> getListaSegnaposti() {
		return listaSegnaposti;
	}

	public boolean verificaCoordinate(int numRiga, int numColonna) {
		if (numRiga >= this.numRiga || numColonna >= this.numColonna)
			return false;
		return true;
	}

	public void addSegnaposto(EntitySegnaposto segnaposto) {
		listaSegnaposti.add(segnaposto);
	}

	public String visualizzaMessaggioErrore() {
		return "Mossa non valida";
	}

}
