/**Classe che estende la classe EntityGiocatore aggiungendo l'attributo color usato per modellare il segnaposto sulla JFrame BattagliaNavale. 
 * Il costruttore di questa classe viene richiamato da GestorePartite ogni volta che viene creata una nuova partita e assegna randomicamente un 
 * colore ad ogni giocatore in gara.
 * 
 */
package entity;

import java.awt.Color;

public class EntityGiocatoreColor extends EntityGiocatore {
	/**color: attributo di tipo Color assegnato randomicamente ad ogni Giocatore in gara per modellare graficamente il segnaposto.
	 * 
	 */
	private Color color;
	/**Costruttore della classe EntityGiocatoreColor, crea un nuovo Giocatore assegnandogli un colore.
	 * 
	 * @param nome : nome del Giocatore, fornito in input dall'Amministratore.
	 * @param selection : intero che gestisce lo switch case, passato in input come numero random tra 1 e 10 dalla classe GestorePartite.
	 */
	public EntityGiocatoreColor(String nome, int selection) {
		super(nome);
		switch (selection) {
		case 1:
			color = Color.red;
			break;
		case 2:
			color = Color.blue;
			break;
		case 3:
			color = Color.black;
			break;
		case 4:
			color = Color.green;
			break;
		case 5:
			color = Color.pink;
			break;
		case 6:
			color = Color.orange;
			break;
		case 7:
			color = Color.darkGray;
			break;
		case 8:
			color = Color.yellow;
			break;
		case 9:
			color = Color.lightGray;
			break;
		case 10:
			color = Color.magenta;
			break;
		}
	}
	/**Metodo get per l'attributo color
	 * 
	 * @return: il colore associato all'istanza di EntityGiocatoreColor su cui viene invocato il metodo.
	 */

	public Color getColor() {
		return this.color;
	}
}
