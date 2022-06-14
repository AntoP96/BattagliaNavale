/**La classe GestorePartite è il controller di questa applicazione. Esso gestisce il flusso di una partita di Battaglia Navale 
 * Si occupa di creare una nuova partita in base agli input forniti dall'Amministratore e quindi crea il campo da gioco, posiziona il bersaglio
 * e aggiunge i giocatori alla partita. Inoltre si occupa di gestire la creazione dei turni e restituisce delle stringhe di controllo
 * che vengono interpretate al livello boundary in modo da comunicare con i Giocatori le varie fasi della partita.
 * Inoltre questa classe ha anche il compito di salvare i dati su database come quelli riguardanti i giocatori creati.
 * 
 */
package control;

import java.util.ArrayList;
import java.util.Random;
import entity.*;
import java.awt.Color;

public class GestorePartite {
	/**partitaCorrente: attributo di EntityPartita che si riferisce alla partita in corso.
	 * 
	 */
	private EntityPartita partitaCorrente;
	/**listaGiocatoriColori: attributo di tipo ArrayList<EntityGiocatoreColor> che mantiene le informazioni relative ai giocatori in gara
	 * e ai colori ad essi assegnati in fase di inizializzazione.
	 * 
	 */
	ArrayList<EntityGiocatoreColor> listaGiocatoriColori = new ArrayList<>();

	public GestorePartite() {

	}

	public void interrogaStatoPartita() {
		
	}

	public void visualizzaElencoPosizioni() {
		
	}

	public void mostraElencoPartite() {
		
	}
	/**Metodo che consente l'inizializzazione di una nuova partita da parte dell'Amministratore che inserisci i dati nell'apposito form presente
	 * nella JFrame della classe InizializzaPartita. Questo metodo richiama il costruttore della classe EntityPartita, prima però crea 
	 * tutti gli oggetti necessari: la lista dei giocatori con i rispettivi colori, la griglia, posiziona il bersaglio e setta il numero di tentativi massimo.
	 * Questo metodo si occupa anche di salvare i giocatori appena inseriti sul database.
	 * @param listaGiocatori: lista dei giocatori in gara passato in input dall'Amministratore
	 * @param numTentativiMax: numero di tentativi massimo passato in input dall'Amministratore
	 * @param numRighe: numero di righe della griglia passato in input dall'Amministratore
	 * @param numColonne: numero di colonne della griglia passato in input dall'Amministratore
	 * @param numRigaBersaglio: posizione riga del bersaglio passato in input dall'Amministratore
	 * @param numColonnaBersaglio: posizione colonna del bersaglio passato in input dall'Amministratore
	 * @param idPartita: idPartita passato dalla classe InizializzaPartita che mantiene una memoria del numero di partite create.
	 */
	public void inizializzaPartita(ArrayList<String> listaGiocatori, int numTentativiMax, int numRighe, int numColonne,
			int numRigaBersaglio, int numColonnaBersaglio,int idPartita) {
		ArrayList<EntityGiocatore> listaGiocatoriInPartita = new ArrayList<>();

		int randomSelection = 0;
		Random random = new Random();
		for (String nomeGiocatore : listaGiocatori) {
			EntityGiocatore giocatore = new EntityGiocatore(nomeGiocatore);
			randomSelection = (random.nextInt(10));
			EntityGiocatoreColor giocatoreColor = new EntityGiocatoreColor(nomeGiocatore, randomSelection);
			listaGiocatoriColori.add(giocatoreColor);
			giocatore.salvaGiocatoreSuDB();
			listaGiocatoriInPartita.add(giocatore);
		}
		EntityGriglia griglia = new EntityGriglia(numRighe, numColonne);
		EntityBersaglio bersaglio = new EntityBersaglio(numRigaBersaglio, numColonnaBersaglio);
		this.partitaCorrente = new EntityPartita(listaGiocatoriInPartita, numTentativiMax, griglia, bersaglio,idPartita);
		griglia.setIdPartita(partitaCorrente.getIdPartita());

	}
	/**Metodo che si occupa di creare un nuovo turno e lanciare il metodo gestisciTurno sull'oggetto EntityTurno appena creato.
	 * Questo metodo viene richiamato dalla classe BattagliaNavale quando il giocatore che deve effettuare la mossa per primo inserisce i dati 
	 * numRiga e numColonna in input nel formDialog. 
	 * 
	 * @param nomeGiocatore: nome del giocatore che deve effettuare per primo la mossa.
	 * @param idTurno: id del turno da creare
	 * @param numRiga: numRiga inserito dal Giocatore.
	 * @param numColonna: numColonna inserito dal Giocatore.
	 * @return
	 */
	public String effettuaMossa(String nomeGiocatore, int idTurno, int numRiga, int numColonna) {

		return partitaCorrente.creaNuovoTurno(idTurno)
				.gestisciTurno(partitaCorrente.getGiocatoreFromLista(nomeGiocatore), numRiga, numColonna);

	}
	/**Metodo che restituisce i giocatori in gara sotto forma di lista.
	 * 
	 * @return: la lista dei giocatori in gara
	 */
	public ArrayList<String> restituisciNomeGiocatori() {
		ArrayList<String> listaNomiGiocatori = new ArrayList<>();
		for (EntityGiocatore giocatore : partitaCorrente.getListaGiocatori()) {
			listaNomiGiocatori.add(giocatore.getNome());
		}
		return listaNomiGiocatori;
	}
	/**Metodo che restituisce il colore associato al giocatore fornito in input attraverso l'attributo listaGiocatoriColori
	 * 
	 * @param nomeGiocatore : nome del giocatore.
	 * @return : colore associato a quel giocatore.
	 */
	public Color getColorFromGiocatore(String nomeGiocatore) {
		for (EntityGiocatoreColor giocatore : listaGiocatoriColori) {
			if (giocatore.getNome() == nomeGiocatore)
				return giocatore.getColor();
		}
		return null;
	}
	/**Metodo get per l'attributo partitaCorrente
	 * 
	 * @return: restituisce la partita corrente.
	 */
	public EntityPartita getPartitaCorrente() {
		return partitaCorrente;
	}
	/**Restituisce l'id della nuova partita da creare attraverso la classe PartitaDB richiamata dal metodo di EntityPartita selectIdPartitaDaDB()
	 * 
	 * @return: l'id da assegnare alla nuova partita.
	 */
	public int getIdPartitaDaCreare() {
		partitaCorrente=new EntityPartita();
		return partitaCorrente.selectIdPartitaDaDB();
	}
}
