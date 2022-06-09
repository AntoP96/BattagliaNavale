package entity;

import java.util.ArrayList;
import database.DBGiocatore;


public class EntityGiocatore {
	private final String nome;
	private ArrayList<EntityPartita> listaPartite;
	private EntitySegnaposto segnaposto;
	private ArrayList<EntityMossa> listaMosse;
	
public EntityGiocatore(String nome) {
	this.nome=nome;
	listaPartite=new ArrayList<>();
	listaMosse=new ArrayList<>();
}
public ArrayList<EntityMossa> getListaMosse(){
	return listaMosse;
}
public void salvaGiocatoreSuDB() {
	DBGiocatore giocatoreDB=new DBGiocatore(nome);
	giocatoreDB.salvaSuDB();
		
	
}
public void setPartitaGiocata(EntityPartita partita) {
	listaPartite.add(partita);
}
public void addMossaInLista(EntityMossa mossa) {
	listaMosse.add(mossa);
}
public void effettuaMossa(int numRiga,int numColonna) {
	segnaposto.spostaSegnaposto(numRiga, numColonna);
	
}
public void setSegnaposto(EntitySegnaposto segnaposto) {
	this.segnaposto=segnaposto;
}
public String getNome() {
	return nome;
}
}
