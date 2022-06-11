package entity;

import java.util.ArrayList;
import java.util.Set;

public class EntityTurno {
	private int idTurno;
	private EntityPartita partita;
	private ArrayList<EntityMossa> listaMosse;

	public EntityTurno(int idTurno, EntityPartita partita) {
		this.idTurno = idTurno;
		this.partita = partita;
		listaMosse = new ArrayList<>();
	}

	public void addMossa(EntityMossa mossa) {
		listaMosse.add(mossa);
	}

	public void incrementaTentativiGiocatore(EntityGiocatore giocatore) {
		partita.incrementaTentativiGiocatore(giocatore);
	}

	public String gestisciTurno(EntityGiocatore giocatore, int numRiga, int numColonna) {
		if (!partita.verificaNumTentativi(giocatore))
			return partita.visualizzaMessaggioErrore();
		else {
			if (!(partita.getGriglia().verificaCoordinate(numRiga, numColonna)))
				return partita.getGriglia().visualizzaMessaggioErrore();
			else {
				EntityMossa mossa = new EntityMossa(numRiga, numColonna, giocatore, partita);
				addMossa(mossa);
				giocatore.effettuaMossa(numRiga, numColonna);
				if (partita.getBersaglio().verificaSeColpito(numRiga, numColonna)) {
					partita.salvaPartitaSuDB(giocatore.getNome());
					partita.getGriglia().salvaGrigliaSuDB();
					partita.getBersaglio().salvaBersaglioSuDB();
					return giocatore.getNome();
				} else
					incrementaTentativiGiocatore(giocatore);

				return "Turno finito";
			}
		}
	}
}