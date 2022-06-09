package entity;

public class EntitySegnaposto {
	private static int idSegnaposto=0;
	private int numRiga;
	private int numColonna;
	private EntityGiocatore giocatore;
	private EntityGriglia griglia;
public EntitySegnaposto(EntityGiocatore giocatore) {
	this.giocatore=giocatore;
	numRiga=0;
	numColonna=0;
	idSegnaposto++;
}
public void spostaSegnaposto(int numRiga,int numColonna) {
	this.numRiga=numRiga;
	this.numColonna=numColonna;
     
	}

public int getNumRiga() {
	return numRiga;
}
public int getNumColonna() {
	return numColonna;
}

}


