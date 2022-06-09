package entity;

public class EntityMossa {
	private static int idMossa=0;
	private int numRiga;
	private int numColonna;
	private EntityGiocatore giocatore;
	private EntityTurno turno;
	private EntityPartita partita;
public EntityMossa(int numRiga,int numColonna,EntityGiocatore giocatore,EntityPartita partita) {
	this.numRiga=numRiga;
	this.numColonna=numColonna;
	this.giocatore=giocatore;
	this.partita=partita;
	idMossa++;
}
}
