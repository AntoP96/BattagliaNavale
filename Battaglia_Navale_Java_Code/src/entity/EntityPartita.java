package entity;

import java.util.*;
import database.DBPartita;
public class EntityPartita {
	private static  Integer idPartita=0;
	private final int numTentativiMax;
	private HashMap<EntityGiocatore,Integer> listaGiocatoriAndTentativi;
    private ArrayList<EntityMossa> listaMosse;
    private final EntityGriglia griglia;
    private final EntityBersaglio bersaglio;
    private ArrayList<EntityTurno> listaTurni;
public EntityPartita(ArrayList<EntityGiocatore> listaGiocatori,int numTentativiMax,EntityGriglia griglia,
		EntityBersaglio bersaglio) {
	this.griglia=griglia;
	this.bersaglio=bersaglio;
	this.numTentativiMax=numTentativiMax;
	
	listaGiocatoriAndTentativi=new HashMap<>();
	for(EntityGiocatore giocatore : listaGiocatori) {
		giocatore.setSegnaposto(new EntitySegnaposto(giocatore));
		listaGiocatoriAndTentativi.put(giocatore,0);
	}
	listaMosse=new ArrayList<>();
	listaTurni=new ArrayList<>();
	idPartita++;
	
}	
public Integer getIdPartita() {
	return idPartita;
}
public void salvaPartitaSuDB(String nomeVincitore) {
	ArrayList<String> listaGiocatori=new ArrayList<>();
	for(EntityGiocatore giocatore: listaGiocatoriAndTentativi.keySet()) {
		listaGiocatori.add(giocatore.getNome());
	}
	DBPartita partitaDB=new DBPartita(numTentativiMax,listaGiocatori,nomeVincitore);
	partitaDB.salvaSuDB();
	
}
public void registraPartitaGiocata() {
	// DA FARE
}
public HashMap<EntityGiocatore,Integer> calcolaTentativiRimasti(){
    HashMap<EntityGiocatore,Integer> listaGiocatoriAndTentativiRimasti=new HashMap<>();   
	for(EntityGiocatore giocatore : listaGiocatoriAndTentativi.keySet()) {
		listaGiocatoriAndTentativiRimasti.put(giocatore,(numTentativiMax-(listaGiocatoriAndTentativi.get(giocatore))));
       }
		return listaGiocatoriAndTentativiRimasti;
}
public void visualizzaElencoPosizioni() {
	//DA FARE???
}
public void mostraElencoPartite() {
	//DA FARE????
}
public void interrogaStatoPartita() {
	//DA FARE????
}
public boolean verificaNumTentativi(EntityGiocatore giocatore) {
	if(listaGiocatoriAndTentativi.get(giocatore)>=numTentativiMax)
		return false;
	return true;
}
public String visualizzaMessaggioErrore() {
	return "Tentativi esauriti";
}

public EntityTurno creaNuovoTurno(int idTurno) {
	EntityTurno turno=new EntityTurno(idTurno,this);
	this.aggiungiTurno(turno);
	return turno;
}
public boolean verificaTermineTurno(String risultato) {
	for(EntityGiocatore giocatore: listaGiocatoriAndTentativi.keySet()) 
		if(risultato.equals(giocatore.getNome()))
				return false;
		
	return true;
}
public void aggiungiTurno(EntityTurno turno) {
	this.listaTurni.add(turno);
}
public Set<EntityGiocatore> getListaGiocatori(){
	return listaGiocatoriAndTentativi.keySet();
	
}
public EntityGriglia getGriglia() {
	return griglia;
}
public EntityBersaglio getBersaglio() {
	return bersaglio;
}
public HashMap<EntityGiocatore,Integer> getListaGiocatoriAndTentativi() {
	return listaGiocatoriAndTentativi;
}
public void incrementaTentativiGiocatore(EntityGiocatore giocatore) {
	listaGiocatoriAndTentativi.replace(giocatore,listaGiocatoriAndTentativi.get(giocatore)+1);
}
public EntityGiocatore getGiocatoreFromLista(String nomeGiocatore) {
	for(EntityGiocatore giocatore : listaGiocatoriAndTentativi.keySet()) {
		if(giocatore.getNome().equals(nomeGiocatore))
			return giocatore;
	}
	return null;
}
}

