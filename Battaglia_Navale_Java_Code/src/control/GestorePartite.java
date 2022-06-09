package control;
import java.util.ArrayList;
import java.util.Random;
import entity.*;
import java.awt.Color;

public class GestorePartite {
	private EntityPartita partitaCorrente;
	ArrayList<EntityGiocatoreColor> listaGiocatoriColori=new ArrayList<>();
	public GestorePartite() {
		
	}
	public void interrogaStatoPartita() {
	   //DA FARE??
	}
	public void visualizzaElencoPosizioni() {
	   //DA FARE??
	}
	public void mostraElencoPartite() {
	   //DA FARE???
	}
	public void inizializzaPartita(ArrayList<String> listaGiocatori,int numTentativiMax,int numRighe,int numColonne,int numRigaBersaglio,int numColonnaBersaglio) {
		ArrayList<EntityGiocatore> listaGiocatoriInPartita=new ArrayList<>();
		
		int randomSelection=0;
		Random random=new Random();
		for(String nomeGiocatore : listaGiocatori) {
			EntityGiocatore giocatore=new EntityGiocatore(nomeGiocatore);
			randomSelection=(random.nextInt(10));
			EntityGiocatoreColor giocatoreColor=new EntityGiocatoreColor(nomeGiocatore,randomSelection);
			listaGiocatoriColori.add(giocatoreColor);
			giocatore.salvaGiocatoreSuDB();
			listaGiocatoriInPartita.add(giocatore);
		}
		EntityGriglia griglia=new EntityGriglia(numRighe,numColonne);
		EntityBersaglio bersaglio=new EntityBersaglio(numRigaBersaglio,numColonnaBersaglio);
		this.partitaCorrente=new EntityPartita(listaGiocatoriInPartita,numTentativiMax,griglia,bersaglio);
	   
	   
	}
	public String effettuaMossa(String nomeGiocatore,int idTurno,int numRiga,int numColonna) {
		
		return partitaCorrente.creaNuovoTurno(idTurno).gestisciTurno(partitaCorrente.getGiocatoreFromLista(nomeGiocatore),numRiga,numColonna);
		
	}
	public ArrayList<String> restituisciNomeGiocatori(){
		ArrayList<String> listaNomiGiocatori=new ArrayList<>();
		for(EntityGiocatore giocatore:partitaCorrente.getListaGiocatori()) {
			listaNomiGiocatori.add(giocatore.getNome());
		}
		return listaNomiGiocatori;
	}
	public Color getColorFromGiocatore(String nomeGiocatore) {
		for(EntityGiocatoreColor giocatore: listaGiocatoriColori) {
			if(giocatore.getNome()== nomeGiocatore)
				return giocatore.getColor();
		}
		return null;
	}
}
