
package entity;

import java.util.*;
import database.DBPartita;
/**La classe EntityPartita modella la singola partita del gioco Battaglia Navale. Essa contiene tutti gli attributi relativi alla partita e alla sua creazione
 * Essa si preoccupa di mantenere i dati relativi alla partita in corso e a verificare che il numero di tentativi di un Giocatore non sia superiore al massimo
 * La partita viene gestita attraverso la creazione del Turno che si occupa di gestire poi la logica della Mossa effettuata da ogni giocatore.
 * 
 * 
 */
public class EntityPartita {
	/**idPartita: attributo private e final di tipo intero che rappresenta l'id della partita.
	 * 
	 */
	private  Integer idPartita;
	/**numTentativiMax: attributo private e final che rappresenta il numero di tentativi massimo relativo a questa istanza di EntityPartita.
	 * 
	 */
	private  int numTentativiMax;
	/**listaGiocatoriAndTentativi: attributo di tipo HashMap<EntityGiocatore,Integer> che mantiene la corrispondenza dei rispettivi giocatori
	 *  e tentativi effettuati
	 * nel corso di questa partita.
	 */
	private HashMap<EntityGiocatore, Integer> listaGiocatoriAndTentativi;
	/**listaMosse: attributo di tipo ArrayList<EntityMossa> che conserva tutte le mosse effettuate in questa partita dai Giocatori in gara.
	 * 
	 */
	private ArrayList<EntityMossa> listaMosse;
	/**griglia: attributo di tipo EntityGriglia che rappresenta la griglia associata a questa partita.
	 * 
	 */
	private  EntityGriglia griglia;
	/**bersaglio: attributo di tipo EntityBersaglio che rappresenta il bersaglio associato a questa partita.
	 * 
	 */
	private  EntityBersaglio bersaglio;
	/**listaTurni: attributo di tipo ArrayList<EntityTurno> che rappresenta la lista dei turni di questa partita.
	 * 
	 */
	private ArrayList<EntityTurno> listaTurni;
	/**Costruttore della classe EntityPartita invocato dall'istanza della classe GestorePartite quando l'Amministratore chiede di creare una nuova partita.
	 * Viene settata idPartita,la griglia, il bersaglio su di essa e il numero di tentativi massimo consentiti in questa partita.
	 * Successivamente viene assegnato un segnaposto a ciascun giocatore in gara e inizializzati gli attributi listaGiocatoriAndTentativi, listaMosse e listaTurni.
	 * 
	 * 
	 * @param listaGiocatori
	 * @param numTentativiMax
	 * @param griglia
	 * @param bersaglio
	 * @param idPartita
	 */
	/**Costruttore vuoto utilizzato dalla classe GestorePartite per ottenere l'id della nuova partita da creare.
	 * 
	 */
	public EntityPartita() {
		
	}
	public EntityPartita(ArrayList<EntityGiocatore> listaGiocatori, int numTentativiMax, EntityGriglia griglia,
			EntityBersaglio bersaglio,Integer idPartita) {
		this.griglia = griglia;
		this.bersaglio = bersaglio;
		this.numTentativiMax = numTentativiMax;
		this.idPartita=idPartita;
		listaGiocatoriAndTentativi = new HashMap<>();
		for (EntityGiocatore giocatore : listaGiocatori) {
			giocatore.setSegnaposto(new EntitySegnaposto(giocatore));
			listaGiocatoriAndTentativi.put(giocatore, 0);
		}
		listaMosse = new ArrayList<>();
		listaTurni = new ArrayList<>();
		

	}
	/**metodo get per l'attributo idPartita
	 * 
	 * @return: l'id della partita su cui viene invocato il metodo.
	 */
	public Integer getIdPartita() {
		return idPartita;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idPartita);
	}
	/**metodo equals sull'attributo idPartita
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityPartita other = (EntityPartita) obj;
		return Objects.equals(idPartita, other.idPartita);
	}
	/**Metodo che permette il salvataggio di una partita conclusa sul database attraverso il metodo salvaSuDB() della classe DBPartita.
	 * Questo metodo viene invocato dalla classe EntityTurno sulla partita corrente al termine di essa:
	 *  quando è stato colpito il bersaglio o quando sono stati esauriti i tentativi da tutti i giocatori.
	 * 
	 * @param nomeVincitore: vincitore della partita, presente solo se il bersaglio viene colpito prima del superamento del numero di tentativi massimo.
	 */
	public void salvaPartitaSuDB(String nomeVincitore) {
		
		for (EntityGiocatore giocatore : listaGiocatoriAndTentativi.keySet()) {
			DBPartita partitaDB = new DBPartita(idPartita,numTentativiMax, giocatore.getNome(), nomeVincitore);
			partitaDB.salvaSuDB();
		}
	}

	public void registraPartitaGiocata() {
		
	}
	/**Metodo che restituisce il numero di tentativi rimasti per ogni giocatore 
	 * 
	 * @return: la corrispondenza tra giocatore e tentativi rimasti con la collezione HashMap<EntityGiocatore,Integer>
	 */
	public HashMap<EntityGiocatore, Integer> calcolaTentativiRimasti() {
		HashMap<EntityGiocatore, Integer> listaGiocatoriAndTentativiRimasti = new HashMap<>();
		for (EntityGiocatore giocatore : listaGiocatoriAndTentativi.keySet()) {
			listaGiocatoriAndTentativiRimasti.put(giocatore,
					(numTentativiMax - (listaGiocatoriAndTentativi.get(giocatore))));
		}
		return listaGiocatoriAndTentativiRimasti;
	}

	public void visualizzaElencoPosizioni() {
		
	}

	public void mostraElencoPartite() {
		
	}

	public void interrogaStatoPartita() {
		
	}
	/**Metodo invocato dalla classe EntityTurno come verifica preliminare di una mossa di un Giocatore. 
	 * Questo metodo verifica che il Giocatore non abbia effettuato un numero di tentativi superiore al massimo consentito.
	 * 
	 * @param giocatore: giocatore di cui verificare i tentativi effettuati.
	 * @return: boolean che indica il risultato della verifica(se false restituisce un messaggio di errore attraverso visualizzaMessaggioErrore()
	 */
	public boolean verificaNumTentativi(EntityGiocatore giocatore) {
		if (listaGiocatoriAndTentativi.get(giocatore) >= numTentativiMax)
			return false;
		return true;
	}
	/**Metodo che restituisce una stringa di errore che viene poi interpretata ai livelli superiori per essere mostrata al Giocatore.
	 * 
	 * @return : Stringa di errore.
	 */
	public String visualizzaMessaggioErrore() {
		return "Tentativi esauriti";
	}
	/**Metodo che crea un nuovo turno all'interno di questa partita e lo aggiunge all'attributo listaTurni attraverso il metodo aggiungiTurno()
	 * 
	 * @param idTurno: id del turno da creare
	 * @return: restituisce l'istanza della classe EntityTurno appena creata.
	 */
	public EntityTurno creaNuovoTurno(int idTurno) {
		EntityTurno turno = new EntityTurno(idTurno, this);
		this.aggiungiTurno(turno);
		return turno;
	}
	/**
	 * Metodo che aggiunge il turno appena creato nell'attributo listaTurni.
	 * @param turno: turno appena creato.
	 */

	public void aggiungiTurno(EntityTurno turno) {
		this.listaTurni.add(turno);
	}
	/**Metodo get che restituisce il keySet della HasHMap listaGiocatoriAndTentativi.
	 *
	 * @return: Set contenente i giocatori in gara.
	 */
	public Set<EntityGiocatore> getListaGiocatori() {
		return listaGiocatoriAndTentativi.keySet();

	}
	/**Metodo get per l'attributo griglia
	 * 
	 * @return : restituisce la griglia associata a questa partita.
	 */
	public EntityGriglia getGriglia() {
		return griglia;
	}
	/**Metodo get per l'attributo bersaglio
	 * 
	 * 	@return : il bersaglio associato a questa partita
	 */
	public EntityBersaglio getBersaglio() {
		return bersaglio;
	}
	/**Metodo get per l'attributo listaGiocatoriAndTentativi
	 * 
	 * @return : restituisce la corrispondenza tra giocatori e tentativi effettuati sotto forma di collezione.
	 */
	public HashMap<EntityGiocatore, Integer> getListaGiocatoriAndTentativi() {
		return listaGiocatoriAndTentativi;
	}
	/**Metodo che modifica l'attributo listaGiocatoriAndTentativi incrementando di un'unità il valore associato alla chiave Giocatore.
	 * 
	 * @param giocatore: giocatore di cui incrementare i tentativi effettuati.
	 */
	public void incrementaTentativiGiocatore(EntityGiocatore giocatore) {
		listaGiocatoriAndTentativi.replace(giocatore, listaGiocatoriAndTentativi.get(giocatore) + 1);
	}
	/**Metodo che restituisce il giocatore in formato Stringa dall'attributo listaGiocatoriAndTentativi. 
	 * 
	 * @param nomeGiocatore : nome del giocatore da cercare.
	 * @return : il nome del giocatore se presente in questa partita.
	 */
	public EntityGiocatore getGiocatoreFromLista(String nomeGiocatore) {
		for (EntityGiocatore giocatore : listaGiocatoriAndTentativi.keySet()) {
			if (giocatore.getNome().equals(nomeGiocatore))
				return giocatore;
		}
		return null;
	}
	/**Metodo get per l'attributo numTentativiMax
	 * 
	 * @return: il numero di tentativi massimo relativo all'istanza di questa classe su cui è stato invocato il metodo.
	 */
	public int getNumTentativiMax() {
		return numTentativiMax;
	}
	/**Seleziona l'id della nuova partita da creare, controllando il numero di partite già create nel database.
	 * 
	 * @return: id da assegnare alla nuova partita.
	 */
	public int selectIdPartitaDaDB() {
		DBPartita partitaDB=new DBPartita();
		return(partitaDB.selectIdPartitaDaDB());
	}
	public void setTentativiGiocatore(EntityGiocatore giocatore,int tentativi) {
		listaGiocatoriAndTentativi.replace(giocatore, tentativi);
	}
}
